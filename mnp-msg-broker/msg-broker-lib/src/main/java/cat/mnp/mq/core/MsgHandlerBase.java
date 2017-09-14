/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.mq.core;

import cat.io.BackupFileHandler;
import cat.mnp.mvno.dao.MvnoMsgDao;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 *
 * @author HP-CAT
 */
public class MsgHandlerBase extends BackupFileHandler implements MsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(MsgHandlerBase.class);
    private String filePath;
    private String filenameFormat;
    private String fileDateFormat;
    private String fileEncoding;
    private String fileLineSeparator;
    private MvnoMsgDao mvnoMsgDao;
    private Unmarshaller jaxbUnMarshaller;
    private Marshaller jaxbMarshaller;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilenameFormat() {
        return filenameFormat;
    }

    public void setFilenameFormat(String filenameFormat) {
        this.filenameFormat = filenameFormat;
    }

    public String getFileDateFormat() {
        return fileDateFormat;
    }

    public void setFileDateFormat(String fileDateFormat) {
        this.fileDateFormat = fileDateFormat;
    }

    public String getFileEncoding() {
        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public String getFileLineSeparator() {
        return fileLineSeparator;
    }

    public void setFileLineSeparator(String fileLineSeparator) {
        this.fileLineSeparator = StringEscapeUtils.unescapeJava(fileLineSeparator);
    }

    public MvnoMsgDao getMvnoMsgDao() {
        return mvnoMsgDao;
    }

    public void setMvnoMsgDao(MvnoMsgDao mvnoMsgDao) {
        this.mvnoMsgDao = mvnoMsgDao;
    }

    public Unmarshaller getJaxbUnMarshaller() {
        return jaxbUnMarshaller;
    }

    public void setJaxbUnMarshaller(Unmarshaller jaxbUnMarshaller) {
        this.jaxbUnMarshaller = jaxbUnMarshaller;
    }

    public Marshaller getJaxbMarshaller() {
        return jaxbMarshaller;
    }

    public void setJaxbMarshaller(Marshaller jaxbMarshaller) {
        this.jaxbMarshaller = jaxbMarshaller;
    }

    @Override
    public void processMsg(Message msg) throws Exception {
        processMsg(new String(msg.getBody()));
    }
    
    // online support
    @Override
    public void processMsg(javax.jms.Message msg) throws Exception {
        processMsg(new String(msg.toString()));
    }

    @Override
    public void processMsg(List<Message> msgList) throws Exception {
        for (Message msg : msgList) {
            processMsg(msg);
        }
    }

    @Override
    public void processMsg(File file) throws Exception {
        try {
            String msgString = FileUtils.readFileToString(file, fileEncoding);
            processMsg(msgString);
            moveFileToDirectory(file, getBackupPath());
        } catch (Exception ex) {
            logger.error(String.format("Error while processing file %s: ", file.getAbsolutePath()), ex);
            moveFileToDirectory(file, getErrorPath());
        }
    }

    public void processMsg(String msgString) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
