/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.zip.core.support;

import cat.mnp.integration.file.ExtendedFileHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import java.io.File;
import java.io.IOException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.util.StringUtils;

/**
 *
 * @author CATr
 */
public class Zip4jTransformer {

    private static final Logger logger = LoggerFactory.getLogger(ZipFileTransformer.class);
    private String extension = "zip";

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Transformer
    public File transform(File input, @Header(name = ExtendedFileHeaders.PASSWORD, required = false) String password) throws ZipException, IOException {
        String zipFilename = String.format("%s.%s", FilenameUtils.getBaseName(input.getName()), extension);
        File tmpFile = File.createTempFile(input.getName(), "-tmp.zip");
        FileUtils.deleteQuietly(tmpFile);
        
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

        if (StringUtils.hasText(password)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
            parameters.setPassword(password);
        }

        logger.info("Zipping {} to {}", input.getAbsolutePath(), tmpFile.getAbsolutePath());
        ZipFile zipFile = new ZipFile(tmpFile);

        if (input.isFile()) {
            zipFile.createZipFile(input, parameters);
        } else if (input.isDirectory()) {
            zipFile.createZipFileFromFolder(input, parameters, false, 0);
        }
        
        File output = FileUtils.getFile(input.getParentFile(), zipFilename);
        FileUtils.deleteQuietly(output);
        logger.info("Moving zip output from {} to {}", zipFile.getFile().getAbsolutePath(), output.getAbsolutePath());
        FileUtils.moveFile(zipFile.getFile(), output);
        
        return output;
    }
}
