<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <style type="text/css">
        <%@include file="/static/bootstrap.css"%>
        <%@include file="/static/app.css"%>
    </style>
</head>

<body>
<div class="generic-container">
    <form:form method="POST" modelAttribute="userForm">
        <h2>Registration</h2>
        <div class="row-cols-3">
            <form:input type="text" path="login" placeholder="Username"
                        autofocus="true"></form:input>
            <form:errors path="username" cssClass="label"></form:errors>
                ${usernameError}
        </div>
        <br>
        <div class="row-cols-3">
            <form:input type="password" path="password" placeholder="Password"></form:input>
        </div>
        <br>
        <div class="row-cols-3">
            <form:input type="text" path="wallet" placeholder="Wallet"></form:input>
        </div>
        <br>
        <button type="submit" class="btn btn-success">Register</button>
    </form:form>
    <br>
    <a href="/" class="btn btn-primary btn-sm">Main</a>
</div>
</body>
</html>