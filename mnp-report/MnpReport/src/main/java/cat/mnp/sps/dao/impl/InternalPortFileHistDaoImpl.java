/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sps.dao.impl;

import cat.mnp.sps.dao.InternalPortFileDao;
import cat.mnp.sps.hibernate.InternalPortHist;
import cat.mnp.util.file.reader.impl.InternalPortCsvFileReaderImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class InternalPortFileHistDaoImpl implements InternalPortFileDao {

    private static Logger logger = LoggerFactory.getLogger(InternalPortFileDao.class);
    private File inputDir;
    private String fileEncoding;
    private String[] extensions;

    @Override
    public List<InternalPortHist> listAll() throws Exception {
        List<InternalPortHist> internalPortHistList = new ArrayList<InternalPortHist>();
        
        for (Iterator<File> it = FileUtils.listFiles(inputDir, extensions, false).iterator(); it.hasNext();) {
            File file = it.next();
            internalPortHistList.addAll(read(file));
        }
        
        return internalPortHistList;
    }

    @Override
    public List<InternalPortHist> read(String filename) throws Exception {
        return read(new File(filename));
    }

    @Override
    public List<InternalPortHist> read(File file) throws Exception {
        InternalPortCsvFileReaderImpl reader = new InternalPortCsvFileReaderImpl(file, fileEncoding);
        
        
        return null;
    }

    @Override
    public void backup() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}