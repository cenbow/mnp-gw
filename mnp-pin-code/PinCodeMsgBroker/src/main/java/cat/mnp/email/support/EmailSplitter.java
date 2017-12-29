/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.email.support;

import cat.mnp.email.integration.MailAttachmentBackupHeaders;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.Message;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.support.MessageBuilder;

/**
 *
 * @author CATr
 */
public class EmailSplitter {

    private String monthFormat;
    private String dateFormat;
    private DateTimeFormatter monthFormatter;
    private DateTimeFormatter dateFormatter;

    public void init() {
        monthFormatter = DateTimeFormatter.ofPattern(monthFormat);
        dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    public void setMonthFormat(String monthFormat) {
        this.monthFormat = monthFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Splitter
    public List<Message<?>> splitIntoMessages(final List<EmailFragment> emailFragments) {

        final List<Message<?>> messages = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (EmailFragment emailFragment : emailFragments) {
            Message<?> message = MessageBuilder.withPayload(emailFragment.getData())
                .setHeader(FileHeaders.FILENAME, emailFragment.getFilename())
                .setHeader(MailAttachmentBackupHeaders.DIRECTORY, emailFragment.getDirectory())
                .setHeader(MailAttachmentBackupHeaders.MONTH_STRING, now.format(monthFormatter))
                .setHeader(MailAttachmentBackupHeaders.DATE_STRING, now.format(dateFormatter))
                .build();
            messages.add(message);
        }

        return messages;
    }
}
