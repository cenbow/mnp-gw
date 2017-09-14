/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.file;

import java.io.File;

/**
 *
 * @author HP-CAT
 */
public interface FileSender {

    public void send(File... files) throws Exception;
    public void send(String body, File... files) throws Exception;
}
