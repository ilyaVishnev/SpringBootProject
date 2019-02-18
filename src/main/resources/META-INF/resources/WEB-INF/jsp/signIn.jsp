<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 18.09.2018
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>sighIn</title>
</head>
<body>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:url var="loginUrl" value="/sign" />
<form action="/sign" method="post" class="form-horizontal">
    Login : <input type="text" name="username"></br>
    Password : <input type="password" name="password"></br>
    <input type="submit" name="submit" value="Вход">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
</body>
</html>
