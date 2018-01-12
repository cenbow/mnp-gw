/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sftp.dao;

import cat.io.BackupFileHandler;
import cat.mnp.file.FileSender;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.util.Assert;

/**
 *
 * @author HP-CAT
 */
public class SftpAdapter extends BackupFileHandler implements FileSender {

    private static final Logger logger = LoggerFactory.getLogger(SftpAdapter.class);
    private MessageChannel inputChannel;
    private long timeout;

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

    @Override
    public void send(File... files) throws Exception {
        logger.info("File list size: {}", files.length);
        for (File file : files) {
            Assert.isTrue(file.exists(), String.format("File '%s' does not exist.", file.getName()));

            long fileSize = FileUtils.sizeOf(file);
            String fileSizeString = FileUtils.byteCountToDisplaySize(fileSize);

            long startTime = System.currentTimeMillis();
            logger.info("Transfering '{}' file [{}] over stp", file.getName(), fileSizeString);

            final Message<File> message = MessageBuilder.withPayload(file).build();

            boolean isSent = false;
            try {
            			isSent = inputChannel.send(message, timeout);
            } catch (Exception ex) {
                moveFileToDirectory(file, getErrorPath());
                logger.error(String.format("Error while sending file '%s' over stp in %s ms", file.getName(), (System.currentTimeMillis() - startTime)), ex);
                throw ex;
            }

            if (isSent == true) {
                moveFileToDirectory(file, getBackupPath());
                logger.info("Successfully transferred '{}' file over stp in {} ms", file.getName(), (System.currentTimeMillis() - startTime));
            } else {
                moveFileToDirectory(file, getErrorPath());
                throw new Exception(String.format("Cannot send file '%s' over stp in %s ms", file.getName(), (System.currentTimeMillis() - startTime)));
            }
        }
    }

    @Override
    public void send(String body, File... files) throws Exception {
        send(files);
    }
}
