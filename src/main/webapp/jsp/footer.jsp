<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="ResourceBundle/Footer/Footer"/>

<!-- Footer -->
<footer class="footer mt-auto py-3 bg-light d-flex">
    <div class="container justify-content-center align-content-center">
        <div class="row">
            <div class="col-md-4">
                &copy;2020-2021 BananaSuprema <br/>
                POWERED BY Banani in Fiore
            </div>
            <div class="col-md-4">

            </div>
            <div class="col-md-4">
                <fmt:message key="changeLanguage"/>
                <ul>
                    <li><a href="?cookieLocale=en"><fmt:message key="langEng" /></a></li>
                    <li><a href="?cookieLocale=it"><fmt:message key="langIt" /></a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>
<!-- Footer -->