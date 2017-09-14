<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="title" value="Please Login" />

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
                <div class="large-4 columns large-centered">
                    <h3>${title}</h3>
                    <h4 class="subheader"></h4>
                </div>
            </div>
        </header>
        <div class="row">
            <section class="large-4 columns large-centered">
                <form action="j_spring_security_check" method="post" >
                    <c:if test="${message != null}">
                        <div class="alert-box alert"><strong>${message}</strong></div>
                            </c:if>
                    <div class="row">
                        <div class="large-4 columns">
                            <label class="right inline" for="j_username">Username:</label>
                        </div>
                        <div class="large-8 columns">
                            <input id="j_username" name="j_username" type="text" autofocus="true"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="large-4 columns">
                            <label class="right inline" for="j_password">Password:</label>
                        </div>
                        <div class="large-8 columns">
                            <input id="j_password" name="j_password" type="password"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="large-12 columns">
                            <button type="submit" class="small button right">Sign in</button>
                        </div>
                    </div>
                </form>
            </section>
        </div>
        <jsp:include page="../component/footer.jsp" />
    </body>
</html>
