/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.io;

import java.io.File;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class BackupFileHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(BackupFileHandler.class);
    private String backupPath;
    private String errorPath;
    private String pathDateFormat;

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    public String getPathDateFormat() {
        return pathDateFormat;
    }

    public void setPathDateFormat(String pathDateFormat) {
        this.pathDateFormat = pathDateFormat;
    }
    
    public void moveFileToDirectory(File file, String path) {
        try {
            String d = DateFormatUtils.format(new Date(), getPathDateFormat());
            String newFilePath = String.format("%s/%s", path, d);
            
            FileUtils.deleteQuietly(new File(FilenameUtils.concat(newFilePath, file.getName())));/*Delete if exist*/
            FileUtils.moveFileToDirectory(file, new File(newFilePath), true);

            logger.info("'{}' file moved to '{}'", file.getName(), newFilePath);
        } catch (Exception ex) {
            logger.error("Cannot move '{}' file moved to '{}'", file, path, ex);
        }
    }
}
