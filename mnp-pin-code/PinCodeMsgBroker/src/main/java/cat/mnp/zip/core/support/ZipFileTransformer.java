/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.zip.core.support;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;

/**
 *
 * @author anuchitr
 */
public class ZipFileTransformer {
    
    private static Logger logger = LoggerFactory.getLogger(ZipFileTransformer.class);
    private String extension = "zip";

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    @Transformer
    public File transform(File input) throws IOException {
        String fullPath = FilenameUtils.getFullPath(input.getAbsolutePath());
        String zipFilename = String.format("%s.%s", FilenameUtils.getBaseName(input.getName()), extension);
        
        Map<String, String> env = new HashMap<>(); 
        env.put("create", "true");
        URI zipUri = URI.create(String.format("jar:file:///%s", FilenameUtils.concat(fullPath, zipFilename).replace("\\", "/")));
        
        Path zipPath = Paths.get(fullPath, zipFilename);
        logger.info("Zipping {} to {}", input.getAbsolutePath(), zipPath);
        try (FileSystem zipFS = FileSystems.newFileSystem(zipUri, env)) {
            Path externalPath = input.toPath();
            Path pathInZip = zipFS.getPath(String.format("/%s", input.getName()));
            Files.copy(externalPath, pathInZip, StandardCopyOption.REPLACE_EXISTING);
        }
        return zipPath.toFile();
    }
    
}