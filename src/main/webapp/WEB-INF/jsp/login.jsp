<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign in</title>
    <style type="text/css">
        <%@include file="/static/bootstrap.css"%>
        <%@include file="/static/app.css"%>
    </style>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div class="generic-container">
    <form method="POST" action="/login">
        <h2>Sign In</h2>
        <span class="label">${param.error}</span>
        <div class="row">
            <div class="col-5"><input name="username" type="text" placeholder="Username" class="form-control"
                                      autofocus="true"/></div>
            <div class="col-5"><input name="password" class="form-control" type="password" placeholder="Password"/>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-2"><input type="submit" class="btn btn-success" value="Sign In"/></div>
            <div class="col-2"><a href="/registration" class="btn btn-secondary">Register</a></div>
        </div>
    </form>
    <br>
    <a href="/" class="btn btn-primary btn-sm">Main</a>
</div>

</body>
</html>
