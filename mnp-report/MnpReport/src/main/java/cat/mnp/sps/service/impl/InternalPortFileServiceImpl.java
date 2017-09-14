/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sps.service.impl;

import cat.mnp.om.dao.PortCompleteDao;
import cat.mnp.sps.dao.InternalPortMsgDao;
import cat.mnp.sps.hibernate.InternalPortHist;
import cat.mnp.sps.service.InternalPortFileService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class InternalPortFileServiceImpl implements InternalPortFileService {

    private static Logger logger = LoggerFactory.getLogger(InternalPortFileServiceImpl.class);

    private InternalPortMsgDao internalPortMsgDao;
    private PortCompleteDao portCompleteDao;

    public InternalPortMsgDao getInternalPortMsgDao() {
        return internalPortMsgDao;
    }

    public void setInternalPortMsgDao(InternalPortMsgDao internalPortMsgDao) {
        this.internalPortMsgDao = internalPortMsgDao;
    }

    public PortCompleteDao getPortCompleteDao() {
        return portCompleteDao;
    }

    public void setPortCompleteDao(PortCompleteDao portCompleteDao) {
        this.portCompleteDao = portCompleteDao;
    }

    @Override
    public List<InternalPortHist> listAll() throws Exception {
        List<InternalPortHist> internalPortHist = new ArrayList<InternalPortHist>();
        
        return internalPortHist;
    }

    @Override
    public List<InternalPortHist> read(String filename) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<InternalPortHist> read(File file) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveMsg(List msgList) throws Exception {
        if (msgList == null) {
            return;
        }
        logger.info("saving portCompleteDao");
        portCompleteDao.saveBatch(msgList);
        logger.info("saved portCompleteDao size: {}", msgList.size());
    }
}
