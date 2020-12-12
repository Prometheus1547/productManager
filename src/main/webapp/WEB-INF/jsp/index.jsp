<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Main page</title>
    <style type="text/css">
        <%@include file="/static/bootstrap.css"%>
        <%@include file="/static/app.css"%>
    </style>
</head>
<body>
<div class="generic-container">
    <h5>Login:${pageContext.request.userPrincipal.name}</h5>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login" class="btn btn-primary">Sign In</a></h4>
        <h4><a href="/registration" class="btn btn-primary">Register</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout" class="btn btn-danger">Log Out</a></h4>
        <h4><a href="/products/${pageContext.request.userPrincipal.name}" class="btn btn-primary">Buy Products</a></h4>
    </sec:authorize>
</div>
</body>
</html>