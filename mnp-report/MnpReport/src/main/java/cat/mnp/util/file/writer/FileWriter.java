/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.util.file.writer;

/**
 *
 * @author HP-CAT
 */
public interface FileWriter {
    
    public void writeNext(String[] strings);
    
    public void close() throws Exception ;
    
}
