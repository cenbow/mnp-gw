<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:set var="title" value="MNP Report Home" />
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
    <head>
        <jsp:include page="component/head.jsp" />
        <title>${title}</title>
    </head>
    <body class="antialiased">
        <jsp:include page="component/navbar.jsp" />
        <header>
            <div class="row">
                <div class="large-8 columns">
                    <h1><a href="index.php">Foundation 3 Documentation</a></h1>
                    <h4 class="subheader">Everything you need to know to build for the future.</h4>
                </div>
            </div>
        </header>
        <div class="row">
            <section id="sidebar" role="complementary" class="large-3 columns">
                <!-- Existing Nav Structure -->
                <dl class="tabs vertical hide-on-phones">
                    <dd class="active"><a href="index.php">Getting Started</a></dd>
                    <dd><a href="installing.php">CSS Version</a></dd>
                    <dd><a href="gem-install.php">Gem Versions</a></dd>
                    <dd><a href="grid.php">The Grid</a></dd>
                    <dd><a href="typography.php">Typography</a></dd>
                    <dd><a href="buttons.php">Buttons</a></dd>
                    <dd><a href="forms.php">Forms</a></dd>
                    <dd><a href="navigation.php">Navigation</a></dd>
                    <dd><a href="tabs.php">Tabs</a></dd>
                    <dd><a href="elements.php">Elements</a></dd>
                    <dd><a href="orbit.php">Orbit</a></dd>
                    <dd><a href="reveal.php">Reveal</a></dd>
                    <dd><a href="javascripts.php">Javascripts</a></dd>
                    <dd><a href="support.php">Support</a></dd>
                </dl>
            </section>
            <section role="main" class="large-9 columns">
                <div class="row">

                    <h2>Getting Started</h2>
                    <h5 class="subheader">Whether you are using Scss or CSS, we've made it easy to start a project so that you can help take over the world one responsive site at a time.</h5>
                    <p>Foundation is developed in <a href="http://www.sass-lang.com">Sass</a>, which is powerful CSS pre-processor that helps you write cleaner, more organized, CSS that you can more easily maintain over time without the typical headaches of vanilla CSS. On top of our minimal styling, we've written powerful Javascript plugins that will make useful interactions easier to implement across screen sizes.</p>

                    <hr>

                    <ul class="accordion">
                        <li class="active">
                            <div class="title">
                                <h5>Accordion Panel 1</h5>
                            </div>
                            <div class="content">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                            </div>
                        </li>
                        <li>
                            <div class="title">
                                <h5>Accordion Panel 2</h5>
                            </div>
                            <div class="content">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                            </div>
                        </li>
                        <li>
                            <div class="title">
                                <h5>Accordion Panel 3</h5>
                            </div>
                            <div class="content">
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                            </div>
                        </li>
                    </ul>

                    <hr>

                    <div class="panel">
                        <h3>Get going!</h3>
                        <h5 class="subheader">Now that you understand the gist of what Foundation is and how it works, it's time to start a project! We've got two different ways for you to build projects with Foundation, a Compass Gem using Scss or a with plain CSS.</h5>
                        <a href="compass.php" class="button">Installing the Gem</a></li>
                        <a href="quickstart.php" class="button secondary">Quickstart with CSS</a></li>
                    </div>
                </div>
            </section>
        </div>
        <hr />
        <jsp:include page="component/script.jsp" />
        <jsp:include page="component/footer.jsp" />
    </body>
</html>