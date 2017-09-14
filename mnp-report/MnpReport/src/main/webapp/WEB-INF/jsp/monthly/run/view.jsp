<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="cat.mnp.report.service.ReportService" %>

<c:set var="title" value="MNP Monthly Report - Run" />
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
    <head>
        <jsp:include page="../../component/head.jsp" />
        <title>${title}</title>
    </head>
    <body class="antialiased">
        <jsp:include page="../../component/navbar.jsp" />
        <header>
            <div class="row">
                <div class="large-12 columns">
                    <h1>${title}</h1>
                    <h4 class="subheader"></h4>
                </div>
            </div>
        </header>
        <div class="row">
            <jsp:include page="../../component/monthly/sidebar.jsp" />
            <section role="main" class="large-10 columns">
                <form id="runReportForm" name="runReportForm" action="${reportPageUrl}/${reportType}/" method="post" accept-charset="UTF-8" enctype="UTF-8">
                    <div class="row collapse">
                        <input name="reportFormat" value="html" type="hidden"/>
                        <div class="large-2 columns">
                            <span class="prefix">ReportMonth: </span>
                        </div>
                        <div class="large-2 columns">
                            <input name="reportMonth" value="${reportMonth}" title="ReportMonth" class="required" type="text" data-date="YYYY-MM"/>
                        </div>
                        <div class="large-1 columns">
                            <input name="submitType" class="button prefix" type="submit" value="List"/>
                        </div>
                        <div class="large-7 columns"></div>
                    </div>

                    <c:if test="${createFutureReportHashMap != null && runFutureReportHashMap != null}">
                        <section id="runReportContainer">
                            <div class="row" style="display: none;">
                                <div class="large-6 columns"><a title="View error" href="status.json?reportMonth=<c:out value="${reportMonth}"/>" target="_blank"><small class="error" style="margin-top: 0px;">Error updating report status</small></a></div>
                                <div class="large-6 columns"></div>
                            </div>
                            <div class="row">
                                <div class="large-7 columns">
                                    <table id="runReportStatusTable" style="width: 100%">
                                        <thead>
                                        <th width="10%" style="text-align: center;"><input id="checkAllReport" type="checkbox" title="Check All" style="margin-bottom: 0;"/></th>
                                        <th width="65%" style="text-align: center;"><div>Report Name</div></th>
                                        <th width="25%" style="text-align: center;"><div>Report Status</div></th>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="reportName" items="${reportNameList}">
                                                <tr>
                                                    <td style="text-align: center;"><input name="runReportName" type="checkbox" style="margin-bottom: 0;" value="<c:out value="${reportName}"/>"/></td>
                                                    <td>${reportName}</td>
                                                    <td style="text-align: center;" data-report-name="<c:out value="${reportName}"/>"></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="large-5 columns"></div>
                            </div>
                            <div id="runReportNameValidate" class="row" style="display: none;"><div class="large-6 columns"><small class="error">At least one report is required.</small></div></div>
                            <div class="row">
                                <div class="large-7 columns">
                                    <input name="submitType" class="small button" type="submit" value="Run Report"/>
                                    <input name="submitType" class="small button right alert" type="submit" value="Cancel Report"/>
                                </div>
                                <div class="large-5 columns"></div>
                            </div>
                        </section>
                    </c:if>
                </form>
            </section>
        </div>
        <hr />
        <div id="loaderImg" style="display: none;">
            <div class="row"><div class="large-2 columns centered"><img src="${staticUrl}/img/loader/ajax-loader.gif"/></div></div>
        </div>
        <jsp:include page="../../component/script.jsp" />
        <script src="${staticUrl}/js/reports/utils.js?version=${version}"></script>
        <script src="${staticUrl}/js/reports/run.js?version=${version}"></script>
        <jsp:include page="../../component/footer.jsp" />
    </body>
</html>