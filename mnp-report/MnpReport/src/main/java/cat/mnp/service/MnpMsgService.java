/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author HP-CAT
 */
public interface MnpMsgService {

    public static String TOTAL_MSG_KEY = "TotalMsg";
    public static String COUNT_LISTED_MSG = "CountListedMsg";
    public static String COUNT_SAVED_MSG = "CountSavedMsg";
    public static String STATUS_KEY = "Status";
    public static String STATUS_QUEUING = "Queuing";
    public static String STATUS_INIT = "Initializing";
    public static String STATUS_LISTING = "Listing";
    public static String STATUS_LISTED = "Listed";
    public static String STATUS_SAVING = "Saving";
    public static String STATUS_DONE = "Done";

    public HashMap getReportStatusServiceByDate(Date date);
    
    public HashMap<String, HashMap> getReportStatusServiceByMonth(Date date);
    
    public void setReportStatusServiceByDate(Date date, String status);
    
    public void resetReportStatusServiceByDate(Date date);
    
    public void resetReportStatusServiceByMonth(Date date);
    
    public List listMsgByDate(Date date) throws Exception;

    public Future<List> listFutureMsgByDate(Date date) throws Exception;

    public int saveMsg(List msgList) throws Exception;

    public int saveMsg(List msgList, Date date) throws Exception;

    public Future saveFutureMsg(Future<List> futureMsgList) throws Exception;

    public Future saveFutureMsg(Future<List> futureMsgList, Date date) throws Exception;
}
