/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP-CAT
 */
@Service
public interface ReportService {
    public static String STATUS_NULL = "-";
    public static String STATUS_KEY = "Status";
    public static String STATUS_INIT = "Initializing";
    public static String STATUS_RUNNING = "Running";
    public static String STATUS_DONE = "Done";
    public static String STATUS_ERROR = "Error";
    public static String STATUS_CANCELLED = "Cancelled";
    public static String STATUS_FAILED = "Failed";
    
    public List<String> listReports(String reportType);
    
    public String getContentType(String format);
    
    public String getCreateReportKeyFormat();
    
    public String getRunReportKeyFormat();
    
    public void setReportParameters(HashMap<String, Object> reportParameters);

    public Future createReport(String reportType, String reportName) throws Exception;

    public Future runReport(Future futureReport, String reportType, String reportName) throws Exception;
    
    public String getStatus(Future futureReport) throws Exception;

    public void renderReport(OutputStream outputStream, String reportType, String reportName, String reportFormat) throws Exception;
    
    public HashMap getReportStatusServiceHashMap(String reportType, String reportName);
    
    public void resetReportStatusServiceHashMap(String reportType, String reportName);
}