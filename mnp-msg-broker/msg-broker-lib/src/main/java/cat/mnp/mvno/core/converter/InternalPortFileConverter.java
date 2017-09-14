/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mvno.core.converter;

import cat.mnp.mq.core.MsgHandlerBase;
import cat.nio.file.PathEvent;
import cat.nio.file.event.PathEventContext;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;

/**
 *
 * @author HP-CAT
 */
public class InternalPortFileConverter extends MsgHandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(InternalPortFileConverter.class);
    private static Pattern filenamePattern = Pattern.compile("^(CAT|WS)_(?<recipient>([^_]+))_(STP|PREPAID|\\d).*$");
//    private static Pattern headerPattern = Pattern.compile("^(?<recipient>([^|]+))\\|H\\s*\\|\\s*(?<date>([^$]+))$");
    private static Pattern headerPattern = Pattern.compile("^(?<recipient>([^H]+))\\|H\\s*\\|\\s*(?<date>([^$]+))$");
    private static Pattern cmdPattern = Pattern.compile("^\\s*(?<type>([^\\s]+)).*((?:DESC=\")(?<desc>([^\"]+))|(MODE)).*(?:USRNUM=\")(?<msisdn>([^\"]+)).*(((?:RNIDX2?=)(?<rnidx>([^(;|\\s|,)]+)))|(CONFIRM)).*$");
    private static Pattern footerPattern = Pattern.compile("^\\s*T\\s*\\|\\s*(?<checksum>([^($|\\s)]+))$");
    private WatchEvent.Kind<Path>[] registeredEventList;
    private AmqpTemplate amqpTemplate;
    private MessageProperties msgProperties;

    public void setRegisteredEventList(WatchEvent.Kind<Path>[] registeredEventList) {
        this.registeredEventList = registeredEventList;
    }

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public MessageProperties getMsgProperties() {
        return msgProperties;
    }

    public void setMsgProperties(MessageProperties msgProperties) {
        this.msgProperties = msgProperties;
    }

    public void handlePathEvents(PathEventContext pathEventContext) {
        for (PathEvent event : pathEventContext.getEvents()) {
            Path path = event.getEventTarget();
            if (ArrayUtils.contains(registeredEventList, event.getType())) {
                logger.info("Processing event '{}' of '{}'", event.getType(), path);
                processMsg(path.toFile());
            } else {
                logger.debug("Ignored event '{}' of '{}'", event.getType(), path);
            }
        }
    }

    @Override
    public void processMsg(File file) {
        try {
            file.deleteOnExit();
            //logger.debug(String.format("start processing file %s ", file.getAbsolutePath()));
            Matcher m = filenamePattern.matcher(file.getName());
            if (m.find()) {
                String recipient = m.group("recipient");
                String msgContent = FileUtils.readFileToString(file, getFileEncoding());
                processMsg(recipient + "|" + msgContent);
                moveFileToDirectory(file, getBackupPath());
            } else {
                logger.error("Error wrong filename pattern: {}", file.getAbsolutePath());
                moveFileToDirectory(file, getErrorPath());
            }
        } catch (Exception ex) {
            logger.error(String.format("Error while processing file %s: ", file.getAbsolutePath()), ex);
            moveFileToDirectory(file, getErrorPath());
        }
    }

    @Override
    public void processMsg(String msgString) throws Exception {
        String[] msgStringList = StringUtils.split(msgString, getFileLineSeparator());
        ArrayList<HashMap<String, String>> cmdList = new ArrayList<>();

        logger.debug("Internal port line size: {}", msgStringList.length);
        if (msgStringList.length > 2) {
            String header = msgStringList[0];
            logger.debug("header: {}", header);
            Matcher m = headerPattern.matcher(header);
            if (!m.find()) {
                throw new Exception("Wrong header format: " + header);
            }
            String recipient = m.group("recipient").toUpperCase();
            String headerDate = m.group("date").replace(" ", "").replace(":", "");

            String footer = msgStringList[msgStringList.length - 1];
            logger.debug("footer: {}", footer);
            m = footerPattern.matcher(footer);
            if (!m.find()) {
                throw new Exception("Wrong footer format: " + footer);
            }
            int checksum = Integer.parseInt(m.group("checksum"));

            for (String msg : (String[]) ArrayUtils.subarray(msgStringList, 1, msgStringList.length - 1)) {
                logger.trace("Internal port msg: {}", msg);

                m = cmdPattern.matcher(msg);
                if (m.find()) {
                    HashMap<String, String> cmd = new HashMap<>();
                    cmd.put("recipient", recipient);
                    cmd.put("date", headerDate);

                    String type = m.group("type");
                    cmd.put("type", type);
                    String msisdn = m.group("msisdn");
                    cmd.put("msisdn", msisdn);
                    String rnidx = m.group("rnidx");
                    cmd.put("rnidx", rnidx);
                    String desc = m.group("desc");
                    cmd.put("desc", desc);
                    cmdList.add(cmd);

                    logger.trace("Internal port cmd: {}", cmd);
                } else {
                    logger.error("Internal port cmd not match: {}", msg);
                }
            }

            if (Integer.compare(cmdList.size(), checksum) != 0) {
                throw new Exception(String.format("Checksum not equal, cmdList: %s, checksum: %s", cmdList.size(), checksum));
            }
            
            logger.info("Internal port cmd size: {}", cmdList.size());
            amqpTemplate.convertAndSend(cmdList);
        } else {
            throw new Exception(String.format("Internal port line size '%s' is not normal, file may be truncated", msgStringList.length));
        }
    }
}
