<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:set var="title" value="MNP Monthly Report - View" />
<c:url var="baseExportReportUrl" value="${reportPageUrl}/${reportType}/${reportMonth}/${reportName}"/>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
    <head>
        <jsp:include page="../component/head.jsp" />
        <title>${title}</title>
    </head>
    <body class="antialiased">
        <jsp:include page="../component/navbar.jsp" />
        <header>
            <div class="row">
                <div class="large-12 columns">
                    <h1>${title}</h1>
                    <h4 class="subheader"></h4>
                </div>
            </div>
        </header>
        <div class="row">
            <jsp:include page="../component/monthly/sidebar.jsp" />
            <section role="main" class="large-10 columns">
                <form id="viewReportForm" name="viewReportForm" action="${reportPageUrl}/${reportType}" method="post" class="custom">
                    <div class="row">
                        <div class="large-12 columns">
                            <div class="row collapse">
                                <input name="reportFormat" value="html" type="hidden"/>
                                <div class="large-2 columns">
                                    <span class="prefix">ReportMonth: </span>
                                </div>
                                <div class="large-2 columns">
                                    <input name="reportMonth" value="${reportMonth}" title="ReportMonth" class="required" type="text" data-date="YYYY-MM"/>
                                </div>
                                <div class="large-2 column">
                                    <span class="prefix">ReportName: </span>
                                </div>
                                <div class="large-3 columns">
                                    <select name="reportName">
                                        <c:forEach var="r" items="${reportNameList}">
                                            <option value="<c:out value="${r}"/>" <c:if test="${reportName == r}">selected="selected"</c:if>>${r}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="large-1 column">
                                    <input class="button prefix" type="submit" value="View"/>
                                </div>
                                <div class="large-1 column">
                                    <a class="button secondary prefix export-btn" href="${reportPageUrl}/${reportType}/${reportMonth}/${reportName}.xlsx" style="display: none;">Xlsx</a>
                                </div>
                                <div class="large-1 column">
                                    <a class="button secondary prefix export-btn" href="${reportPageUrl}/${reportType}/${reportMonth}/${reportName}.pdf" style="display: none;">Pdf</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <section id="viewReportContainer" style="display: none; overflow-x: scroll">${reportBody}</section>

            </section>
        </div>
        <hr />
        <div id="loaderImg" style="display: none;">
            <div class="row"><div class="large-2 columns centered"><img src="${staticUrl}/img/loader/ajax-loader.gif"/></div></div>
        </div>
        <jsp:include page="../component/script.jsp" />
        <script src="${staticUrl}/js/reports/utils.js?version=${version}"></script>
        <script src="${staticUrl}/js/reports/view.js?version=${version}"></script>
        <jsp:include page="../component/footer.jsp" />
    </body>
</html>