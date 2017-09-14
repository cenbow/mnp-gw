/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.ibacss.core;

import cat.mnp.file.FileSender;
import cat.mnp.mq.core.MsgHandlerBase;
import com.google.common.base.Strings;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *
 * @author HP-CAT
 */
public class InternalPortFileGenerator extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(InternalPortFileGenerator.class);
    private MessageConverter messageConverter;
    private FileSender fileSender;

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public FileSender getFileSender() {
        return fileSender;
    }

    public void setFileSender(FileSender fileSender) {
        this.fileSender = fileSender;
    }

    private File getFile(String recipient) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, recipient);
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        Assert.notNull(messageConverter, "messageConverter could not be null");

        logger.debug("Converting internal port msg");
        ArrayList<HashMap<String, String>> msgList = (ArrayList) messageConverter.fromMessage(msg);

        logger.info("Internal port size: {}", msgList.size());
        processMsg(msgList);
    }

    public void processMsg(ArrayList<HashMap<String, String>> msgList) throws Exception {
        ArrayList<String> cmdList = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        logger.info("Generating Cmd from 1 batch");

        String recipient;
        if (!msgList.isEmpty()) {
            recipient = msgList.get(0).get("recipient");
            
            ArrayList<String> tmpCmdList = new ArrayList<>();
            tmpCmdList.addAll(getMvnoMsgDao().queryMsg(msgList));
            for (String cmd : tmpCmdList) {
                if (!Strings.isNullOrEmpty(cmd)) {
                    cmdList.add(cmd);
                }
            }
            logger.info("Generated Cmd from internal port size: {} to Cmd size: {} in {} ms", msgList.size(), cmdList.size(), (System.currentTimeMillis() - startTime));
        } else {
            recipient = "NULL";
        }
        
        cmdList.add(String.format("T|%s\n", cmdList.size()));
        cmdList.add(0, String.format("H|%s\n", DateFormatUtils.format(new Date(), "yyyyMMdd")));

        File file = getFile(recipient);
        String cmdString = StringUtils.collectionToDelimitedString(cmdList, "");
        FileUtils.writeStringToFile(file, cmdString, getFileEncoding());

        fileSender.send(file);
    }
}
