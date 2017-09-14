/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sps.service;

import cat.mnp.om.hibernate.MnpPortComplete;
import cat.mnp.sps.hibernate.InternalPortHist;
import java.io.File;
import java.util.List;

/**
 *
 * @author HP-CAT
 */
public interface InternalPortFileService {

    public List<InternalPortHist> listAll() throws Exception;

    public List<InternalPortHist> read(String filename) throws Exception;

    public List<InternalPortHist> read(File file) throws Exception;

    public void saveMsg(List msgList) throws Exception;
}
