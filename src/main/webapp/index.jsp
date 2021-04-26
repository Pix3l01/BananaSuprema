<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:choose>
    <c:when test="${cookie.containsKey('lang')}">
        <c:set var="lang" scope="session" value="${cookie['lang'].value}"/>
    </c:when>
    <c:otherwise>
        <c:set var="lang" scope="session" value="${pageContext.request.locale}"/>
    </c:otherwise>
</c:choose>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="ResourceBundle/Home/Home"/>

<!DOCTYPE html>
<html class="h-100" lang="${lang}">
<head>
    <!-- meta tags -->
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.css">

    <title><fmt:message key="tabTitle"/></title>
    <link rel="icon" type="image/svg+xml" href="assets/media/faviconBananaSuprema.svg"/>
</head>
<body class="d-flex flex-column h-100">

<jsp:include page="jsp/navbar.jsp"/>

<div class="container-fluid" style="text-align: center; margin-top: 30px;">
    <h1>
        <fmt:message key="coming"/>
    </h1>
</div>


<jsp:include page="jsp/footer.jsp"/>

<!-- Scripts -->

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>