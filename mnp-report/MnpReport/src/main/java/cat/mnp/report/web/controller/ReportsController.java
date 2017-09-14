/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.mnp.report.web.controller;

import cat.mnp.report.service.ReportService;
import cat.mnp.service.MnpMsgService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.commons.lang3.time.DateUtils;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author HP-CAT
 */
@Controller
@RequestMapping("/reports")
public class ReportsController implements ApplicationContextAware, ServletContextAware {

    private static Logger logger = LoggerFactory.getLogger(ReportsController.class);
    private ApplicationContext ac;
    private ServletContext sc;
    private static String[] dateFormatList = {"yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss"};
    @Autowired
    @Qualifier(value = "broadcastMsgService")
    private MnpMsgService broadcastMsgService;
    @Autowired
    @Qualifier(value = "internalPortMsgService")
    private MnpMsgService internalPortMsgService;
    private ReportService dailyReportService;
    private ReportService monthlyReportService;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        this.ac = ac;
    }

    @Override
    public void setServletContext(ServletContext sc) {
        this.sc = sc;
    }

    @Autowired
    @Qualifier(value = "dailyReportService")
    @Scope(value = "request")
    public void setDailyReportService(ReportService dailyReportService) {
        this.dailyReportService = dailyReportService;
    }

    @Autowired
    @Qualifier(value = "monthlyReportService")
    @Scope(value = "request")
    public void setMonthlyReportService(ReportService monthlyReportService) {
        this.monthlyReportService = monthlyReportService;
    }

    @RequestMapping(value = "/daily")
    public String daily(ModelMap model) {
        String reportPage = "/daily/view";
        model.addAttribute("reportPage", reportPage);
        return reportPage;
    }

    @RequestMapping(value = "/monthly/prepare", method = RequestMethod.GET)
    public String prepareMonthly(ModelMap model) {
        String reportPage = "/monthly/prepare";
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("dateList", null);

        return reportPage + "/view";
    }

    @RequestMapping(value = "/monthly/prepare", method = RequestMethod.POST)
    public String prepareMonthlySubmitByMonth(HttpServletRequest request, WebRequest webRequest, ModelMap model, @RequestParam String reportMonth) throws Exception {
        String reportPage = "/monthly/prepare";
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("reportMonth", reportMonth);

        Date startDate = DateUtils.parseDateStrictly(reportMonth, dateFormatList);
        Date endDate = DateUtils.addMonths(startDate, 1);
        ArrayList<Date> dateList = new ArrayList<Date>();
        for (Date d = startDate; d.compareTo(endDate) < 0; d = DateUtils.addDays(d, 1)) {
            dateList.add(d);
        }
        model.addAttribute("dateList", dateList);

        String submitType = webRequest.getParameter("submitType");
        if (submitType == null || submitType.startsWith("View")) {
            logger.debug("View reports");
        } else if (submitType.startsWith("Run")) {
            logger.debug(submitType);
            for (Iterator<Date> it = dateList.iterator(); it.hasNext();) {
                Date d = it.next();
                prepareMonthlySubmitByDateHandle(d);
            }
        }

        return reportPage + "/view";
    }

    @RequestMapping(value = "/monthly/prepare/submitByDate/{dateString}", method = RequestMethod.POST)
    @ResponseBody
    public String prepareMonthlySubmitByDate(ModelMap model, @PathVariable String dateString) throws Exception {
        logger.debug("submitTypeByDate: {}", dateString);
        Date d = DateUtils.parseDateStrictly(dateString, dateFormatList);
        prepareMonthlySubmitByDateHandle(d);

        return "Done";
    }

    public void prepareMonthlySubmitByDateHandle(Date d) throws Exception {
        internalPortMsgService.resetReportStatusServiceByDate(d);
        internalPortMsgService.setReportStatusServiceByDate(d, MnpMsgService.STATUS_QUEUING);
        Future<List> futureListMsg = internalPortMsgService.listFutureMsgByDate(d);
        internalPortMsgService.saveFutureMsg(futureListMsg, d);

        broadcastMsgService.resetReportStatusServiceByDate(d);
        broadcastMsgService.setReportStatusServiceByDate(d, MnpMsgService.STATUS_QUEUING);
        futureListMsg = broadcastMsgService.listFutureMsgByDate(d);
        broadcastMsgService.saveFutureMsg(futureListMsg, d);

    }

    @RequestMapping(value = "/monthly/prepare/status.json", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, HashMap<String, HashMap>> prepareMonthlyStatus(ModelMap model, @RequestParam String reportMonth) throws Exception {
        Date m = DateUtils.parseDateStrictly(reportMonth, dateFormatList);
        HashMap<String, HashMap<String, HashMap>> reportStatusHashMap = new HashMap<String, HashMap<String, HashMap>>();
        reportStatusHashMap.put("broadcastMsgStatus", broadcastMsgService.getReportStatusServiceByMonth(m));
        reportStatusHashMap.put("internalPortMsgStatus", internalPortMsgService.getReportStatusServiceByMonth(m));

        return reportStatusHashMap;
    }

    @RequestMapping(value = "/monthly/run/{reportType}", method = RequestMethod.GET)
    public String runMonthlyView(HttpServletRequest request, ModelMap model, @PathVariable String reportType) {
        String reportPage = "/monthly/run";
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("reportType", reportType);
        model.addAttribute("reportService", monthlyReportService);

        return reportPage + "/view";
    }

    @RequestMapping(value = "/monthly/run/{reportType}", method = RequestMethod.POST)
    public String runMonthlyList(HttpServletRequest request, WebRequest webRequest, ModelMap model, @PathVariable String reportType, @RequestParam String reportMonth) throws Exception {
        String reportPage = "/monthly/run";
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("reportType", reportType);
        model.addAttribute("reportMonth", reportMonth);
        model.addAttribute("reportService", monthlyReportService);

        HttpSession session = request.getSession();

        List<String> runReportNameList = monthlyReportService.listReports(reportType);
        model.addAttribute("reportNameList", runReportNameList);
        HashMap<String, Future<IRunTask>> createFutureReportHashMap = new HashMap<String, Future<IRunTask>>();
        HashMap<String, Future<IRunTask>> runFutureReportHashMap = new HashMap<String, Future<IRunTask>>();
        for (Iterator<String> it = runReportNameList.iterator(); it.hasNext();) {
            String runReportName = it.next();

            String createReportSessionKey = String.format(monthlyReportService.getCreateReportKeyFormat(), reportPage, reportMonth, runReportName);
            Future<IRunTask> createFutureReport = (Future<IRunTask>) session.getAttribute(createReportSessionKey);
            createFutureReportHashMap.put(runReportName, createFutureReport);

            String runReportSessionKey = String.format(monthlyReportService.getRunReportKeyFormat(), reportPage, reportMonth, runReportName);
            Future<IRunTask> runFutureReport = (Future<IRunTask>) session.getAttribute(runReportSessionKey);
            runFutureReportHashMap.put(runReportName, runFutureReport);
        }

        String submitType = webRequest.getParameter("submitType");
        if (submitType == null || submitType.startsWith("List")) {
            logger.debug("List reports");
        } else {
            String[] submitReportNameList = webRequest.getParameterValues("runReportName");

            HashMap<String, Object> p = new HashMap<String, Object>();
            p.put("ReportMonth", reportMonth);
            p.put("ReportPath", sc.getRealPath("/reports"));
            monthlyReportService.setReportParameters(p);

            for (String reportName : submitReportNameList) {
                String createReportSessionKey = String.format(monthlyReportService.getCreateReportKeyFormat(), reportPage, reportMonth, reportName);
                String runReportSessionKey = String.format(monthlyReportService.getRunReportKeyFormat(), reportPage, reportMonth, reportName);
                Future<IRunTask> createFutureReport = createFutureReportHashMap.get(reportName);
                Future<IRunTask> runFutureReport = runFutureReportHashMap.get(reportName);

                if (submitType.startsWith("Run")) {
                    logger.debug("Run reports");
                    if ((createFutureReport == null || createFutureReport.isDone() || createFutureReport.isCancelled())
                            && (runFutureReport == null || runFutureReport.isDone() || runFutureReport.isCancelled())) {

                        monthlyReportService.resetReportStatusServiceHashMap(reportType, reportName);

                        createFutureReport = monthlyReportService.createReport(reportType, reportName);
                        session.setAttribute(createReportSessionKey, createFutureReport);

                        runFutureReport = monthlyReportService.runReport(createFutureReport, reportType, reportName);
                        session.setAttribute(runReportSessionKey, runFutureReport);
                    }
                } else if (submitType.startsWith("Cancel")) {
                    logger.debug("Cancel reports");
                    String createFutureReportStatus = monthlyReportService.getStatus(createFutureReport);
                    if (createFutureReportStatus.startsWith(ReportService.STATUS_RUNNING) || createFutureReportStatus.startsWith(ReportService.STATUS_INIT)) {
                        logger.debug("Getting iRunTask");
                        IRunTask iRunTask = createFutureReport.get();

                        boolean cancelStatus = createFutureReport.cancel(true);
                        logger.debug("Cancel createFutureReport, cancelStatus {} ", cancelStatus);

                        logger.debug("Cancelling reports, status {}]", monthlyReportService.getStatus(createFutureReport));
                        iRunTask.cancel();

                        cancelStatus = runFutureReport.cancel(true);
                        logger.debug("Cancel runFutureReport, cancelStatus {} ", cancelStatus);
                    } else {
                        logger.debug("Cannot cancel report, status: {}, {}", monthlyReportService.getStatus(createFutureReport), monthlyReportService.getStatus(runFutureReport));
                    }
                }
            }
        }

        model.addAttribute("createFutureReportHashMap", createFutureReportHashMap);
        model.addAttribute("runFutureReportHashMap", runFutureReportHashMap);
        return reportPage + "/view";
    }

    @RequestMapping(value = "/monthly/run/{reportType}/status.json", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, HashMap<String, HashMap>> runMonthlyStatus(ModelMap model, WebRequest webRequest, @PathVariable String reportType, @RequestParam String reportMonth) throws Exception {
        HashMap<String, Object> p = new HashMap<String, Object>();
        p.put("ReportMonth", reportMonth);
        monthlyReportService.setReportParameters(p);

        HashMap<String, HashMap<String, HashMap>> reportStatusHashMap = new HashMap<String, HashMap<String, HashMap>>();
        for (String reportName : monthlyReportService.listReports(reportType)) {
            reportStatusHashMap.put(reportName, monthlyReportService.getReportStatusServiceHashMap(reportType, reportName));
        }

        return reportStatusHashMap;
    }

    @RequestMapping(value = "/monthly/view/{reportType}", method = RequestMethod.GET)
    public String viewMonthlyList(HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable String reportType) throws Exception {
        String reportPage = "/monthly/view";
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("reportType", reportType);
        model.addAttribute("reportNameList", monthlyReportService.listReports(reportType));

        return reportPage;
    }

    @RequestMapping(value = "/monthly/view/{reportType}", method = RequestMethod.POST)
    public String viewMonthlyHtml(HttpServletRequest request, HttpServletResponse response, ModelMap model, @PathVariable String reportType, @RequestParam String reportMonth, @RequestParam String reportName, @RequestParam String reportFormat) throws Exception {
        String reportPage = "/monthly/view";
        model.addAttribute("reportPage", reportPage);
        model.addAttribute("reportType", reportType);
        model.addAttribute("reportMonth", reportMonth);
        model.addAttribute("reportName", reportName);
        model.addAttribute("reportNameList", monthlyReportService.listReports(reportType));

        HashMap<String, Object> p = new HashMap<String, Object>();
        p.put("ReportMonth", reportMonth);
        monthlyReportService.setReportParameters(p);

        viewHandle(monthlyReportService, response, model, reportType, reportMonth, reportName, reportFormat);

        return reportPage;
    }

    @RequestMapping(value = "/monthly/view/{reportType}/{reportMonth}/{reportName}.{reportFormat}", method = RequestMethod.GET)
    public void viewMonthlyExport(HttpServletRequest request, HttpServletResponse response, @PathVariable String reportType, @PathVariable String reportMonth, @PathVariable String reportName, @PathVariable String reportFormat) throws Exception {
        HashMap<String, Object> p = new HashMap<String, Object>();
        p.put("ReportMonth", reportMonth);
        monthlyReportService.setReportParameters(p);

        viewHandle(monthlyReportService, response, null, reportType, reportMonth, reportName, reportFormat);
    }

    public void viewHandle(ReportService reportService, HttpServletResponse response, ModelMap model, String reportType, String reportMonth, String reportName, String reportFormat) throws Exception {
        response.setContentType(reportService.getContentType(reportFormat));
        if (reportFormat.equals("html")) {
            StringBuilderWriter sbw = new StringBuilderWriter();
            WriterOutputStream wos = new WriterOutputStream(sbw, "UTF-8");

            reportService.renderReport(wos, reportType, reportName, reportFormat);
            wos.flush();

            model.addAttribute("reportBody", sbw.toString());

            wos.close();
        } else {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + String.format("[%s]%s.%s", reportMonth, reportName, reportFormat) + "\"");
            reportService.renderReport(response.getOutputStream(), reportType, reportName, reportFormat);
        }
    }
}
