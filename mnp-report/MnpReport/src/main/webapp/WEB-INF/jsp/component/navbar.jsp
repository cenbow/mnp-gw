<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:url value="/" var="homeUrl"/>
<c:url var="dailyReportUrl" value="/reports/daily"/>
<c:url var="monthlyReportUrl" value="/reports/monthly"/>
<c:url var="reportPageUrl" value="/reports${reportPage}" scope="request"/>
<c:url var="loginUrl" value="/login"/>
<c:url var="logoutUrl" value="/logout"/>

<div class="contain-to-grid">
    <nav class="top-bar">
        <ul class="title-area">
            <li class="name"><h1><a href="${homeUrl}">MNP Report</a></h1></li>
            <li class="toggle-topbar menu-icon"><a href="#"><span>menu</span></a></li>
        </ul>

        <section class="top-bar-section">
            <sec:authorize access="isAuthenticated()">
                <ul class="left">
                    <li class="divider"></li>
                    <li class="<c:out value="${fn:startsWith(reportPageUrl, dailyReportUrl)?'active':null}" />"><a href="${dailyReportUrl}">Daily</a></li>
                    <li class="divider"></li>
                    <li class="<c:out value="${fn:startsWith(reportPageUrl, monthlyReportUrl)?'active':null}" />"><a href="${monthlyReportUrl}/prepare">Monthly</a></li>
                    <li class="divider"></li>
                </ul>
                <ul class="right">
                    <li class="has-dropdown">
                        <a href="#"><span id="currentUserId"><%=SecurityContextHolder.getContext().getAuthentication().getName()%></span></a>
                        <ul class="dropdown">
                            <li><a href="${logoutUrl}">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <ul class="right">
                    <li><a href="${loginUrl}">Login</a></li>
                </ul>
            </sec:authorize>
        </section>
    </nav>
</div>