<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:set var="title" value="MNP Monthly Report - Prepare Data" />
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
                <form id="prepareReportForm" name="prepareReportForm" action="${reportPageUrl}/" method="post">
                    <div class="row collapse">
                        <input name="reportFormat" value="html" type="hidden"/>
                        <div class="large-2 columns">
                            <span class="prefix">ReportMonth: </span>
                        </div>
                        <div class="large-2 columns">
                            <input name="reportMonth" value="${reportMonth}" title="ReportMonth" class="required" type="text" data-date="YYYY-MM"/>
                        </div>
                        <div class="large-1 columns">
                            <input name="submitType" class="button prefix" type="submit" value="View"/>
                        </div>
                        <div class="large-6 columns"></div>
                        <div class="large-1 columns">
                            <!--<input name="submitType" class="button prefix" type="submit" value="View"/>-->
                            <input id="runPrepareBtn" name="submitType" class="button prefix" type="submit" value="Run"/>
                        </div>
                    </div>

                    <c:if test="${dateList != null}">
                        <section id="prepareReportContainer">
                            <div data-magellan-destination="import" class="row">
                                <div class="large-12 columns">
                                    <div class="row" style="display: none;">
                                        <div class="large-12 columns"><a title="View error" href="status.json?reportMonth=<c:out value="${reportMonth}"/>" target="_blank"><small class="error" style="margin-top: 0px;">Error updating import status</small></a></div>
                                    </div>
                                    <div class="row">
                                        <div class="large-12 columns">
                                            <table id="importReportStatusTable" style="width: 100%">
                                                <thead>
                                                <th width="100" style="text-align: center;">Date</th>
                                                <th width="15%" style="text-align: center;">Port Type</th>
                                                <th width="70%" style="text-align: center;">
                                                    Progress
                                                    <button id="forceEnableRunPrepareBtn" class="tiny button alert right" style="display: none; margin-bottom: 0;">Force Enable Run Button</button></th>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="d" items="${dateList}">
                                                        <tr>
                                                            <td rowspan="2" style="text-align: right; vertical-align: middle;">
                                                                <fmt:formatDate value="${d}" pattern="E, yyyy-MM-dd"/><br/>
                                                                <button name="runPrepareByDateBtn" class="button tiny secondary" type="submit" value="<fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>">Run</button>
                                                            </td>
                                                            <td style="text-align: center;">Internal Port</td>
                                                            <td>
                                                                <div data-port-type="internalPortMsgStatus" data-date="<fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>" class="progress success large-12" style="margin-bottom: 0;"><div class="progress-label">0%</div><span class="meter" style="width: 0%"></span></div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="text-align: center;">External Port</td>
                                                            <td>
                                                                <div data-port-type="broadcastMsgStatus" data-date="<fmt:formatDate value="${d}" pattern="yyyy-MM-dd"/>" class="progress success large-12" style="margin-bottom: 0;"><div class="progress-label">0%</div><span class="meter" style="width: 0%"></span></div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div id="step2Validate" data-magellan-destination="validate" class="row">
                                <div class="large-12 columns">
                                    <h4>Step 2: Validate</h4>
                                    <div class="row">
                                        <div class="large-12 columns">
                                            <table id="importReportStatusTable" style="width: 100%">
                                                <thead>
                                                <th width="100" style="text-align: center;">Date</th>
                                                <th width="15%" style="text-align: center;">Port Type</th>
                                                <th width="70%" style="text-align: center;">
                                                    Progress
                                                    <button id="forceEnableRunPrepareBtn" class="tiny button alert right" style="display: none; margin-bottom: 0;">Force Enable Run Button</button></th>
                                                </thead>
                                                <tbody></tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="large-1 column"></div>
                                        <div class="large-11 columns">
                                            <h6>MVNO Name is null</h6>
                                            <p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="large-1 column"></div>
                                        <div class="large-11 columns">
                                            <h6>Internal Port after port out</h6>
                                            <p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div id="step3Finalize" data-magellan-destination="finalize" class="row">
                                <div class="large-12 columns">
                                    <h4>Step 3: Finalize</h4>
                                    <p><strong>Generate CSV Datasource:</strong> </p>

                                    <div class="row">
                                        <div class="large-1 column"></div>
                                        <div class="large-11 columns">
                                            <h6>MVNO Name is null</h6>
                                            <p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="large-1 column"></div>
                                        <div class="large-11 columns">
                                            <h6>Internal Port after port out</h6>
                                            <p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr/>
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
        <script src="${staticUrl}/js/reports/utils.js?version=${version}" type="text/javascript"></script>
        <script src="${staticUrl}/js/reports/prepare.js?version=${version}" type="text/javascript"></script>
        <jsp:include page="../../component/footer.jsp" />
    </body>
</html>