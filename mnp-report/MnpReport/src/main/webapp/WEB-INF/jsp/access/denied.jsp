<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="title" value="Unauthorized" />

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
    <head>
        <title>${title}</title>
        <jsp:include page="../component/head.jsp" />
    </head>
    <body class="antialiased">
        <jsp:include page="../component/navbar.jsp" />
        <header>
            <div class="row">
                <div class="large-4 columns centered">
                    <h1>${title}</h1>
                    <h4 class="subheader">Access denied!</h4>
                </div>
            </div>
        </header>
        <jsp:include page="../component/footer.jsp" />
    </body>
</html>
