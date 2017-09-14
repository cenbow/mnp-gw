/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.converter.support;

import cat.mnp.email.integration.ExtendedMailHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.FileHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

/**
 *
 * @author anuchitr
 */
public class ByteArrayToMimeMessageTransformer {

    private static final Logger logger = LoggerFactory.getLogger(ByteArrayToMimeMessageTransformer.class);
    
    private JavaMailSender mailSender;
    private FileTypeMap fileTypeMap = MimetypesFileTypeMap.getDefaultFileTypeMap();

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setFileTypeMap(FileTypeMap fileTypeMap) {
        this.fileTypeMap = fileTypeMap;
    }

    @Transformer
    public MimeMessage transform(
        @Header(name = ExtendedMailHeaders.BODY, required = false) String body,
        @Header(name = FileHeaders.FILENAME, required = false) String filename,
        @Payload byte[] fileByte) throws MessagingException, UnsupportedEncodingException {

        boolean allAscii = true;
        for (int i = 0; body != null && i < body.length() && allAscii; i++) {
            allAscii = body.charAt(i) <= 0x7F;
        }

        MimeBodyPart bodyPart;
        if (body == null) {
            bodyPart = null;
        } else if (allAscii) {
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
                InternetHeaders internetHeaders = new InternetHeaders();
                internetHeaders.setHeader("Content-Type", "text/plain; charset=UTF-8");
                internetHeaders.setHeader("Content-Transfer-Encoding", "quoted-printable");
                bodyPart = new MimeBodyPart(internetHeaders, os.toByteArray());
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

        //part 1
        if (bodyPart != null) {
            mp.addBodyPart(bodyPart);
        }

        //part 2
        if (fileByte != null) {
            logger.debug("Attatching file: " + filename);
            MimeBodyPart filePart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(fileByte, fileTypeMap.getContentType(filename));
            filePart.setDataHandler(new DataHandler(source));
            filePart.setFileName(MimeUtility.encodeText(filename));
            mp.addBodyPart(filePart);
        }
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        mimeMessage.setContent(mp);
        
        return mimeMessage;
    }
}
