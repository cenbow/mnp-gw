/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.service.impl;

import cat.mnp.om.dao.PortCompleteDao;
import cat.mnp.report.service.ReportStatusService;
import cat.mnp.service.MnpMsgService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP-CAT
 */
@Service
public class MnpMsgServiceImpl implements MnpMsgService {

    private static Logger logger = LoggerFactory.getLogger(MnpMsgServiceImpl.class);
    protected PortCompleteDao portCompleteDao;
    protected ReportStatusService reportStatusService;

    public PortCompleteDao getPortCompleteDao() {
        return portCompleteDao;
    }

    public void setPortCompleteDao(PortCompleteDao portCompleteDao) {
        this.portCompleteDao = portCompleteDao;
    }

    public ReportStatusService getReportStatusService() {
        return reportStatusService;
    }

    public void setReportStatusService(ReportStatusService reportStatusService) {
        this.reportStatusService = reportStatusService;
    }

    @Override
    public List listMsgByDate(Date date) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int saveMsg(List msgList) throws Exception {
        if (msgList == null) {
            return 0;
        }
        logger.info("saving portCompleteDao");
        int savedCount = portCompleteDao.saveBatch(msgList);
        logger.info("saved portCompleteDao size: {}", savedCount);

        return savedCount;
    }

    @Override
    public int saveMsg(List msgList, Date date) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Async
    @Override
    public Future<List> listFutureMsgByDate(Date date) throws Exception {
        return new AsyncResult<List>(listMsgByDate(date));
    }

    @Async
    @Override
    public Future saveFutureMsg(Future<List> futureMsgList) throws Exception {
        List msgList = futureMsgList.get();
        Integer savedCount = saveMsg(msgList);
        return new AsyncResult(savedCount);
    }

    @Async
    @Override
    public Future saveFutureMsg(Future<List> futureMsgList, Date date) throws Exception {
        List msgList = futureMsgList.get();
        Integer savedCount = saveMsg(msgList, date);
        return new AsyncResult(savedCount);
    }

    @Override
    public HashMap getReportStatusServiceByDate(Date date) {
        String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());
        return (HashMap) reportStatusService.getHashEntries(reportStatusDateKey);
    }

    @Override
    public HashMap<String, HashMap> getReportStatusServiceByMonth(Date startDate) {
        Date endDate = DateUtils.addMonths(startDate, 1);

        HashMap<String, HashMap> reportStatusHashMap = new HashMap<String, HashMap>();
        for (Date d = startDate; d.compareTo(endDate) < 0; d = DateUtils.addDays(d, 1)) {
            String dateString = DateFormatUtils.format(d, reportStatusService.getKeyDateFormat());
            reportStatusHashMap.put(dateString, getReportStatusServiceByDate(d));
        }
        return reportStatusHashMap;
    }

    @Override
    public void resetReportStatusServiceByDate(Date date) {
        String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());
        logger.debug("resetReportStatusServiceByDate: {}", reportStatusDateKey);
        reportStatusService.deleteHashEntries(reportStatusDateKey);
    }

    @Override
    public void setReportStatusServiceByDate(Date date, String status) {
        String reportStatusDateKey = DateFormatUtils.format(date, reportStatusService.getKeyDateFormat());
        reportStatusService.setHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY, status);
    }

    @Override
    public void resetReportStatusServiceByMonth(Date startDate) {
        Date endDate = DateUtils.addMonths(startDate, 1);

        for (Date d = startDate; d.compareTo(endDate) < 0; d = DateUtils.addDays(d, 1)) {
            resetReportStatusServiceByDate(d);
        }
    }
}
