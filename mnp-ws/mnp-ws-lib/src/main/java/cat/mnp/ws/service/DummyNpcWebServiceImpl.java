/**
 * NPCWebService.java
 *
 * This file was auto-generated from WSDL by the Apache Axis2 version: 1.6.2
 * Built on : Apr 17, 2012 (05:33:49 IST)
 */
package cat.mnp.ws.service;

import cat.io.BackupFileHandler;
import cat.mnp.clh.util.NpcMessageValidationHandler;
import com.telcordia.inpac.ws.NPCWebService;
import com.telcordia.inpac.ws.jaxb.MessageHeaderType;
import com.telcordia.inpac.ws.jaxb.NPCDataType;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * NPCWebService java skeleton for the axisService
 */
@Service
public class DummyNpcWebServiceImpl extends BackupFileHandler implements NPCWebService {

    private static final Logger logger = LoggerFactory.getLogger(DummyNpcWebServiceImpl.class);
    private NpcMessageValidationHandler validator;
    private String failPath;
    private String filePath;
    private String filenameFormat;
    private String fileDateFormat;
    private String fileEncoding;

    public NpcMessageValidationHandler getValidator() {
        return validator;
    }

    public void setValidator(NpcMessageValidationHandler validator) {
        this.validator = validator;
    }

    public String getFailPath() {
        return failPath;
    }

    public void setFailPath(String failPath) {
        this.failPath = failPath;
    }

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

    private File getFile(MessageHeaderType messageHeader) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, messageHeader.getMessageID(), messageHeader.getSoapRequestId());
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    private File getFile(String validationCode) {
        String dateString = DateFormatUtils.format(new Date(), getFileDateFormat());
        String filename = String.format(getFilenameFormat(), dateString, validationCode, "");
        return new File(FilenameUtils.concat(getFilePath(), filename));
    }

    @Override
    public String processNPCMsg(String userId, String password, String xml) {
        String validationResult;

        try {
            validationResult = validator.validate(userId, password, xml);

            if (validator.isValidationSuccess(validationResult)) {
                NPCDataType npcDataType = validator.getNpcDataType();
                MessageHeaderType messageHeader = npcDataType.getMessageHeader();

                logger.debug("Writing xml log");
                File file = getFile(messageHeader);
                FileUtils.writeStringToFile(file, xml, getFileEncoding());

                moveFileToDirectory(file, getBackupPath());
            } else {
                logger.debug("Writing fail xml log");
                File file = getFile(validator.getValidationCode());
                FileUtils.writeStringToFile(file, xml, getFileEncoding());
                moveFileToDirectory(file, getFailPath());
            }
        } catch (Exception ex) {
            logger.error("Unexpected Exception: ", ex);
            try {
                logger.debug("Writing error xml log");
                File file = getFile("NPC1008E");
                FileUtils.writeStringToFile(file, xml, getFileEncoding());
                moveFileToDirectory(file, getErrorPath());
            } catch (IOException ex1) {
                logger.error("IOException while writing error xml: ", ex1);
            }
            validationResult = validator.getErrorCodeMapper().get("NPC1008E").replace(validator.getReplaceString1(), ex.getMessage());
        }
        return validationResult;
    }

    @Override
    public void moveFileToDirectory(File file, String path) {
        try {
            String d = DateFormatUtils.format(new Date(), getPathDateFormat());
            String newFilePath = String.format("%s/%s", path, d);

            FileUtils.deleteQuietly(new File(FilenameUtils.concat(newFilePath, file.getName())));/*Delete if exist*/
            FileUtils.moveFileToDirectory(file, new File(newFilePath), true);

            logger.info("'{}' file moved to '{}'", file.getName(), newFilePath);
        } catch (Exception ex) {
            logger.warn("Cannot move '{}' file moved to '{}'", file, path); // just 1 line warn
        }
    }

}
