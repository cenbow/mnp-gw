<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/static" var="staticUrl" scope="request"/>
<c:url value="20130110.0" var="version" scope="request"/>

<meta charset="utf-8" />

<!-- Set the viewport width to device width for mobile -->
<meta name="viewport" content="width=device-width" />
<!-- For third-generation iPad with high-resolution Retina display: 
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../images/favicons/apple-touch-icon-144x144-precomposed.png">
 For iPhone with high-resolution Retina display: 
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../images/favicons/apple-touch-icon-114x114-precomposed.png">
 For first- and second-generation iPad: 
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../images/favicons/apple-touch-icon-72x72-precomposed.png">
 For non-Retina iPhone, iPod Touch, and Android 2.1+ devices: 
<link rel="apple-touch-icon-precomposed" href="../images/favicons/apple-touch-icon-precomposed.png">
 For non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->

<link rel="shortcut icon" href="${staticUrl}/img/ico/favicon_32x32.ico" type="image/x-icon" />

<meta name="keywords" content="MNP, Report" />
<meta name="description" content="MNP Operation Reports GUI" />
<meta name="author" content="Anuchit Ratanaparadorn" />
<meta name="copyright" content="CAT Telecom PLC. Copyright (c) 2013" />

<!-- Included CSS Files -->
<link rel="stylesheet" href="${staticUrl}/css/normalize.css?version=${version}">
<link rel="stylesheet" href="${staticUrl}/css/foundation4.css?version=${version}">
<!--<link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">-->
<!--<link href='http://fonts.googleapis.com/css?family=Fauna+One' rel='stylesheet' type='text/css'>-->

<script src="${staticUrl}/js/vendor/custom.modernizr.js"></script>

<!-- IE Fix for HTML5 Tags -->
<!--[if lt IE 9]>
    <link rel="stylesheet" href="${staticUrl}/css/ie8-foundation4.css?version=${version}">
    <script src="${staticUrl}/js/html5.js"></script>
<![endif]-->