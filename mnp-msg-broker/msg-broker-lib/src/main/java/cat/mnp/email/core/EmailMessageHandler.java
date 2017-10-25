/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.email.core;

import cat.io.BackupFileHandler;
import cat.mnp.file.FileSender;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author HP-CAT
 */
public class EmailMessageHandler extends BackupFileHandler implements FileSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailMessageHandler.class);
    private Map<String, Object> headers;
    private JavaMailSender mailSender;
    private MessageChannel inputChannel;
    private long timeout;
    private String dateFormat;

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MessageChannel getInputChannel() {
        return inputChannel;
    }

    public void setInputChannel(MessageChannel inputChannel) {
        this.inputChannel = inputChannel;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public static Multipart toMimeMultipart(String body, File... attachFiles) throws MessagingException {
        boolean allAscii = true;
        for (int i = 0; i < body.length() && allAscii; i++) {
            allAscii = body.charAt(i) <= 0x7F;
        }

        MimeBodyPart bodyPart;
        if (allAscii) {
            logger.debug("Message is an Ascii Charactor");
            bodyPart = new MimeBodyPart();
            bodyPart.setText(body);
        } else {
            logger.debug("Message is not an Ascii Charactor");
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                try (Writer writer = new OutputStreamWriter(MimeUtility.encode(os, "quoted-printable"), "UTF-8")) {
                    writer.write(body);
                }
                InternetHeaders headers = new InternetHeaders();
                headers.setHeader("Content-Type", "text/plain; charset=UTF-8");
                headers.setHeader("Content-Transfer-Encoding", "quoted-printable");
                bodyPart = new MimeBodyPart(headers, os.toByteArray());
            } catch (IOException | MessagingException ex) {
                logger.warn("Cannot Convert Message to UTF-8");
                StringBuilder sbuf = new StringBuilder(body);
                for (int i = 0; i < sbuf.length(); i++) {
                    if (sbuf.charAt(i) >= 0x80) {
                        sbuf.setCharAt(i, '?');
                    }
                }
                bodyPart = new MimeBodyPart();
                bodyPart.setContent(sbuf.toString(), "text/plain");
            }
        }

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(bodyPart);

        // Part two is attachment
        for (File attachFile : attachFiles) {
            logger.debug("Attatching file: " + attachFile.getAbsolutePath());
            MimeBodyPart filePart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachFile);
            filePart.setDataHandler(new DataHandler(source));
            filePart.setFileName(attachFile.getName());
            mp.addBodyPart(filePart);
        }

        return mp;
    }

    InternetAddress getAddress(String addressStr) throws AddressException {
        return new InternetAddress(addressStr);
    }

    InternetAddress[] parseAddress(String addressStr) throws AddressException {
        return InternetAddress.parse(addressStr, true);
    }

    @Override
    public void send(File... files) throws Exception {
        send(null, files);
    }

    @Override
    public void send(String body, File... files) throws MessagingException {
        Multipart mp = toMimeMultipart(body, files);
        MimeMessage msg = mailSender.createMimeMessage();
        msg.setContent(mp);

        Map<String, Object> tmpHeader;
        if (dateFormat != null) {
            tmpHeader = new HashMap<>();
            String dateString = DateFormatUtils.format(new Date(), dateFormat);
            for (String header : headers.keySet()) {
                String s = headers.get(header).toString().replaceAll(":DATE", dateString);
                tmpHeader.put(header, s);
            }
        } else {
            tmpHeader = headers;
        }

        Message<MimeMessage> message = MessageBuilder.withPayload(msg).copyHeaders(tmpHeader).build();

        boolean isSent = false;
        try {
            logger.info("Sending reports to {}", headers);
            if(true) {
            		logger.warn("Mock email to "+headers); //FIXME: MockUp email sending
            		isSent = true;
            }else {
            		isSent = inputChannel.send(message, timeout);
            }
        } catch (Exception ex) {
            for (File file : files) {
                moveFileToDirectory(file, getErrorPath());
            }
            logger.error("Cannot send reports to " + headers, ex);
            throw ex;
        }

        if (isSent == true) {
            for (File file : files) {
                moveFileToDirectory(file, getBackupPath());
            }
            logger.info("Reports sent to {}", headers);
        } else {
            for (File file : files) {
                moveFileToDirectory(file, getErrorPath());
            }
            logger.error("Cannot send reports to {}", headers);
        }
    }
}
