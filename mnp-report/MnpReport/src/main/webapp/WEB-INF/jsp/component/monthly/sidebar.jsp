<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:url var="prepareMonthly" value="/reports/monthly/prepare"/>
<c:url var="runMonthly" value="/reports/monthly/run"/>
<c:url var="viewMonthly" value="/reports/monthly/view"/>

<c:set var="prepareMonthlyClass" value="${fn:endsWith(prepareMonthly, reportPage)?'active':''}"/>
<c:set var="runMonthlyClass" value="${fn:endsWith(runMonthly, reportPage)?'active':''}"/>
<c:set var="viewMonthlyClass" value="${fn:endsWith(viewMonthly, reportPage)?'active':''}"/>

<div class="large-2 columns">
    <div data-options="one_up: false" data-section="" class="docs section-container accordion">
        <sec:authorize access="hasRole('qc')">
            <section class="section <c:out value="${prepareMonthlyClass}"/>">
                <p class="title"><a href="#prepare">Prepare Data</a></p>
                <div class="content">
                    <ul class="side-nav">
                        <li class="<c:out value="${reportType=='import'&&runMonthlyClass == 'active'?'active':''}"/>"><a href="${prepareMonthly}/import">Import</a></li>
                        <li class="<c:out value="${reportType=='validate'&&runMonthlyClass == 'active'?'active':''}"/>"><a href="${prepareMonthly}/validate">Validate</a></li>
                        <li class="<c:out value="${reportType=='finalize'&&runMonthlyClass == 'active'?'active':''}"/>"><a href="${prepareMonthly}/finalize">Finalize</a></li>
                    </ul>
                </div>
            </section>
            <section class="section <c:out value="${runMonthlyClass}"/>">
                <p class="title"><a href="#run">Run Reports</a></p>
                <div class="content">
                    <ul class="side-nav">
                        <li class="<c:out value="${reportType=='mnp'&&runMonthlyClass == 'active'?'active':''}"/>"><a href="${runMonthly}/mnp">MNP</a></li>
                        <li class="<c:out value="${reportType=='nbtc'&&runMonthlyClass == 'active'?'active':''}"/>"><a href="${runMonthly}/nbtc">NBTC</a></li>
                    </ul>
                </div>
            </section>
        </sec:authorize>
        <sec:authorize access="hasRole('viewer')">
            <section class="section <c:out value="${viewMonthlyClass}"/>">
                <p class="title"><a href="#view">View Reports</a></p>
                <div class="content">
                    <ul class="side-nav">
                        <li class="<c:out value="${reportType=='mnp'&&viewMonthlyClass == 'active'?'active':''}"/>"><a href="${viewMonthly}/mnp">MNP</a></li>
                        <li class="<c:out value="${reportType=='nbtc'&&viewMonthlyClass == 'active'?'active':''}"/>"><a href="${viewMonthly}/nbtc">NBTC</a></li>
                    </ul>
                </div>
            </section>
        </sec:authorize>
    </div>
</div>
