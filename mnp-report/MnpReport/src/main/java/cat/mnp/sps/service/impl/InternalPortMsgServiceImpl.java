/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.sps.service.impl;

import cat.mnp.om.hibernate.MnpPortComplete;
import cat.mnp.service.MnpMsgService;
import cat.mnp.service.impl.MnpMsgServiceImpl;
import cat.mnp.sps.dao.InternalPortMsgDao;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author HP-CAT
 */
public class InternalPortMsgServiceImpl extends MnpMsgServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(InternalPortMsgServiceImpl.class);
    private InternalPortMsgDao internalPortMsgDao;

    public InternalPortMsgDao getInternalPortMsgDao() {
        return internalPortMsgDao;
    }

    public void setInternalPortMsgDao(InternalPortMsgDao internalPortMsgDao) {
        this.internalPortMsgDao = internalPortMsgDao;
    }

    @Override
    public List listMsgByDate(Date date) throws Exception {
        try {
            String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());

            String currentStatus = (String) reportStatusService.getHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY);
            if (currentStatus == null || currentStatus.equals(MnpMsgService.STATUS_QUEUING)) {
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_LISTING);
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.TOTAL_MSG_KEY, 1);
                List list = internalPortMsgDao.listByDate(date);
                if (list.isEmpty() == true) {
                    reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.TOTAL_MSG_KEY, 0);
                    reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_LISTED_MSG, 0);
                } else {
                    reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.TOTAL_MSG_KEY, list.size());
                    reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_LISTED_MSG, list.size());
                }
                reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_LISTED);

                return list;
            } else {
                logger.error("Call listMsg while status: {}", currentStatus);
            }
        } catch (Exception e) {
            logger.error("Error: {}", e);
            throw e;
        }
        return null;
    }

    @Override
    public int saveMsg(List msgList) throws Exception {
        if (msgList == null) {
            return 0;
        }
        logger.info("Saving portCompleteDao size: {}", msgList.size());
        int savedCount = portCompleteDao.saveBatch(msgList);
        logger.info("Saved portCompleteDao size: {}", savedCount);
        return savedCount;
    }

    @Override
    public int saveMsg(List msgList, Date date) throws Exception {
        if (msgList == null) {
            return 0;
        }
        logger.info("Saving portCompleteDao size: {}", msgList.size());
        int savedCount = 0;
        String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());
        reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_SAVING);
        for (MnpPortComplete mnpPortComplete : (List<MnpPortComplete>) msgList) {
            savedCount += portCompleteDao.save(mnpPortComplete);
            reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.COUNT_SAVED_MSG, savedCount);
        }
        reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, MnpMsgService.STATUS_DONE);
        logger.info("Saved portCompleteDao size: {}", savedCount);
        return savedCount;
    }
}
