/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.util.file.reader;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public interface FileReader {
    
    public List readAll() throws IOException;
    
    public void close() throws Exception ;
    
}
