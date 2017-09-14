/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.file;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author HP-CAT
 */
public interface ReportFileWriter {
    
    public void init() throws IOException;
    
    public File getFile() throws IOException;

    public void writeNext(String[] row);
    
    public void writeNext(Map<String, Object> rowMapper);

    public void close() throws Exception;
}
