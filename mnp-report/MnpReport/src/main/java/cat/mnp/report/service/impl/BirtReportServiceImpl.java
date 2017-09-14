/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.service.impl;

import cat.mnp.report.service.ReportService;
import cat.mnp.report.service.ReportStatusService;
import cat.mnp.service.MnpMsgService;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IGetParameterDefinitionTask;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.IScalarParameterDefn;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.spring.core.ReportParameterConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author HP-CAT
 */
@Service
public class BirtReportServiceImpl implements ReportService, ServletContextAware {

    private static Logger logger = LoggerFactory.getLogger(ReportService.class);
    private static final String REPORT_DESIGN_EXT = "rptdesign";
    private IReportEngine birtEngine;
    private ReportStatusService reportStatusService;
    private String reportDateParam;
    private String reportPath;
    private Locale reportLocale;
    private String createReportKeyFormat;
    private String runReportKeyFormat;
    private HashMap<String, Object> reportParameters = new HashMap<String, Object>();

    @Override
    public void setServletContext(ServletContext sc) {
        if (sc != null && reportPath != null) {
            reportPath = sc.getRealPath(reportPath);
        }
    }

    public IReportEngine getBirtEngine() {
        return birtEngine;
    }

    public void setBirtEngine(IReportEngine birtEngine) {
        this.birtEngine = birtEngine;
    }

    public ReportStatusService getReportStatusService() {
        return reportStatusService;
    }

    public void setReportStatusService(ReportStatusService reportStatusService) {
        this.reportStatusService = reportStatusService;
    }

    public String getReportDateParam() {
        return reportDateParam;
    }

    public void setReportDateParam(String reportDateParam) {
        this.reportDateParam = reportDateParam;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public Locale getReportLocale() {
        return reportLocale;
    }

    public void setReportLocale(Locale reportLocale) {
        this.reportLocale = reportLocale;
    }

    @Override
    public String getCreateReportKeyFormat() {
        return createReportKeyFormat;
    }

    public void setCreateReportKeyFormat(String createReportKeyFormat) {
        this.createReportKeyFormat = createReportKeyFormat;
    }

    @Override
    public String getRunReportKeyFormat() {
        return runReportKeyFormat;
    }

    public void setRunReportKeyFormat(String runReportKeyFormat) {
        this.runReportKeyFormat = runReportKeyFormat;
    }

    public HashMap<String, Object> getReportParameters() {
        return reportParameters;
    }

    @Override
    public void setReportParameters(HashMap<String, Object> reportParameters) {
        this.reportParameters = reportParameters;
    }

    @Override
    public String getContentType(String format) {
        return birtEngine.getMIMEType(format);
    }

    @Override
    public List<String> listReports(String reportType) {
        String reportDesignDir = String.format("%s/design/%s", reportPath, reportType);
        logger.debug("reportDesignDir: {}", reportDesignDir);

        ArrayList<String> reportNameList = new ArrayList<String>();
        Collection<File> listFiles = FileUtils.listFiles(new File(reportDesignDir), new String[]{REPORT_DESIGN_EXT}, false);
        logger.debug("listFiles.size(): {}", listFiles.size());
        for (Iterator<File> it = listFiles.iterator(); it.hasNext();) {
            File file = it.next();
            reportNameList.add(StringUtils.stripFilenameExtension(file.getName()));
        }
        return reportNameList;
    }

    @Async
    @Override
    public Future<IRunTask> createReport(String reportType, String reportName) throws Exception {
        String reportStatusDateKey = getReportStatusKey(reportType, reportName);
        try {
            logger.info("Creating Report Runnable: {} {}", reportType, reportName);
            String currentStatus = (String) reportStatusService.getHashValue(reportStatusDateKey, MnpMsgService.STATUS_KEY);

            if (currentStatus == null || !currentStatus.equals(ReportService.STATUS_RUNNING)) {
                reportStatusService.setHashValue(reportStatusDateKey, ReportService.STATUS_KEY, ReportService.STATUS_INIT);
                IReportRunnable iReportRunnable = birtEngine.openReportDesign(String.format("%s/design/%s/%s.rptdesign", reportPath, reportType, reportName));

                logger.debug("Initialize Run Task: {} {}", reportType, reportName);
                IRunTask iRunTask = birtEngine.createRunTask(iReportRunnable);

                logger.debug("Setting Report Parameters: {} {}", reportType, reportName);
                iRunTask.getAppContext().put("ReportPath", getReportDataPath());
                iRunTask.setParameterValues(discoverAndSetParameters(iReportRunnable));

                return new AsyncResult<IRunTask>(iRunTask);
            } else {
                logger.error("Call createReport while status: {}", currentStatus);
            }
        } catch (Exception e) {
            reportStatusService.setHashValue(reportStatusDateKey, ReportService.STATUS_KEY, ReportService.STATUS_ERROR);
            logger.error("Error while creating report:", e);
            throw e;
        }

        return null;
    }

    @Async
    @Override
    public Future<IRunTask> runReport(Future futureReport, String reportType, String reportName) throws Exception {
        IRunTask iRunTask = null;
        String reportStatusDateKey = getReportStatusKey(reportType, reportName);
        String reportDocumentFilename = getReportDocumentFileName(reportType, reportName);
        try {
            reportStatusService.setHashValue(reportStatusDateKey, ReportService.STATUS_KEY, ReportService.STATUS_RUNNING);
            logger.debug("Getting Future Report: {} {}", reportType, reportName);
            iRunTask = ((Future<IRunTask>) futureReport).get();
            logger.info("Running Report: {} {}", reportType, reportName);
            iRunTask.run(reportDocumentFilename);
            String reportStatus = getTaskStatus(iRunTask);
            if (reportStatus.equals(ReportService.STATUS_DONE)) {
                logger.debug("Report Document Created: {} {}", reportType, reportName);
            } else {
                logger.info("Report Error: {}", reportStatus);
                FileUtils.deleteQuietly(new File(reportDocumentFilename));
            }
            reportStatusService.setHashValue(reportStatusDateKey, ReportService.STATUS_KEY, reportStatus);

            iRunTask.close();
        } catch (Exception e) {
            reportStatusService.setHashValue(reportStatusDateKey, ReportService.STATUS_KEY, ReportService.STATUS_ERROR);
            FileUtils.deleteQuietly(new File(reportDocumentFilename));
            logger.error("Error while running report:", e);
            throw e;
        }
        return new AsyncResult<IRunTask>(iRunTask);
    }

    private String getTaskStatus(IRunTask iRunTask) {
        String status;
        if (iRunTask.getCancelFlag()) {
            status = ReportService.STATUS_CANCELLED + " Type II";
        } else {
            switch (iRunTask.getStatus()) {
                case IRunTask.STATUS_CANCELLED:
                    status = ReportService.STATUS_CANCELLED + " Type III";
                    break;
                case IRunTask.STATUS_FAILED:
                    status = ReportService.STATUS_FAILED;
                    break;
                case IRunTask.STATUS_NOT_STARTED:
                    status = "Not started";
                    break;
                case IRunTask.STATUS_RUNNING:
                    status = ReportService.STATUS_RUNNING;
                    break;
                case IRunTask.STATUS_SUCCEEDED:
                    status = ReportService.STATUS_DONE;
                    break;
                default:
                    status = ReportService.STATUS_DONE + " Type II";
            }
        }
        return status;
    }

    @Override
    public String getStatus(Future futureReport) throws Exception {
        String status;
        if (futureReport == null) {
            status = ReportService.STATUS_NULL;
        } else if (futureReport.isCancelled()) {
            status = ReportService.STATUS_CANCELLED;
        } else if (futureReport.isDone()) {
            IRunTask iRunTask = (IRunTask) futureReport.get();
            status = getTaskStatus(iRunTask);
        } else {
            status = ReportService.STATUS_INIT;
        }
        return status;
    }

    private HashMap discoverAndSetParameters(IReportRunnable report) throws Exception {
        HashMap<String, Object> parms = new HashMap<String, Object>();
        IGetParameterDefinitionTask task = birtEngine.createGetParameterDefinitionTask(report);

        @SuppressWarnings("unchecked")
        Collection<IParameterDefnBase> params = task.getParameterDefns(true);
        Iterator<IParameterDefnBase> iter = params.iterator();
        while (iter.hasNext()) {
            IParameterDefnBase param = (IParameterDefnBase) iter.next();
            IScalarParameterDefn scalar = (IScalarParameterDefn) param;
            if (reportParameters.containsKey(param.getName())) {
                parms.put(param.getName(), getParamValueObject(scalar));
            }
        }
        task.close();
        return parms;
    }

    private Object getParamValueObject(IScalarParameterDefn parameterObj) throws Exception {
        String paramName = parameterObj.getName();
        String format = parameterObj.getDisplayFormat();
        ReportParameterConverter converter = new ReportParameterConverter(format, reportLocale);
        return converter.parse((String) reportParameters.get(paramName), parameterObj.getDataType());
    }

    private String getReportStatusKey(String reportType, String reportName) {
        return String.format("%s/%s/%s", reportType, (String) reportParameters.get(reportDateParam), reportName);
    }

    private String getReportDocumentFileName(String reportType, String reportName) {
        return String.format("%s/%s.rptdocument", getReportDocumentPath(reportType), reportName);
    }

    private String getReportDocumentPath(String reportType) {
        return String.format("%s/document/%s/%s", reportPath, reportType, reportParameters.get(reportDateParam));
    }

    private String getReportDataPath() {
        return String.format("%s/document/data/%s", reportPath, reportParameters.get(reportDateParam));
    }

    @Override
    public void renderReport(OutputStream outputStream, String reportType, String reportName, String reportFormat) throws Exception {
        IRenderOption options = new RenderOption();
        options.setOutputStream(outputStream);
        options.setOutputFormat(reportFormat);

        String reportDocumentFilename = getReportDocumentFileName(reportType, reportName);
        File reportDocumentFile = new File(reportDocumentFilename);
        logger.info("{} {}", reportDocumentFile.canRead(), reportDocumentFile.canWrite());
        if (!reportDocumentFile.exists()) {
            outputStream.write("No report to render.".getBytes("UTF-8"));
        } else if (!reportDocumentFile.canRead() || !reportDocumentFile.canWrite()) {
            outputStream.write("Cannot access report file, please try again later.".getBytes("UTF-8"));
        } else {
            try {
                IReportDocument iReportDocument = birtEngine.openReportDocument(reportDocumentFilename);
                if (iReportDocument.isComplete()) {
                    logger.debug("Initialize Render Task");
                    IRenderTask iRenderTask = birtEngine.createRenderTask(iReportDocument);

                    logger.info("Rendering Report");
                    iRenderTask.setRenderOption(options);
                    iRenderTask.render();
                    logger.debug("Finish Rendering Report");
                    iRenderTask.close();
                } else {
                    outputStream.write("Report is still running, please try again later.".getBytes("UTF-8"));
                }
                iReportDocument.close();
            } catch (EngineException e) {
                outputStream.write(e.getMessage().getBytes("UTF-8"));
            }
        }
    }

    @Override
    public HashMap getReportStatusServiceHashMap(String reportType, String reportName) {
        String reportStatusDateKey = getReportStatusKey(reportType, reportName);
        return (HashMap) reportStatusService.getHashEntries(reportStatusDateKey);
    }

    @Override
    public void resetReportStatusServiceHashMap(String reportType, String reportName) {
        String reportStatusDateKey = getReportStatusKey(reportType, reportName);
        reportStatusService.deleteHashEntries(reportStatusDateKey);
    }
}
