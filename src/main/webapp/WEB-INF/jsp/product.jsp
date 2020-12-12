<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>New Product</title>
    <style type="text/css">
        <%@include file="/static/bootstrap.css"%>
        <%@include file="/static/app.css"%>
    </style>
</head>

<body>
<div class="generic-container">
    <form:form method="POST" modelAttribute="productForm">
        <h2>Add New Product</h2>
        <div class="row-cols-3">
            <form:input type="text" path="name" placeholder="Name"
                        autofocus="true"></form:input>
            <form:errors path="name" cssClass="label"></form:errors>
                ${nameError}
        </div>
        <br>
        <div class="row-cols-3">
            <form:input type="text" path="price" placeholder="Price"
                        autofocus="true"></form:input>
            <form:errors path="price" cssClass="label"></form:errors>
                ${priceError}
        </div>
        <br>
        <div class="row-cols-3">
            <form:input type="text" path="stock" placeholder="Stock"
                        autofocus="true"></form:input>
            <form:errors path="stock" cssClass="label"></form:errors>
                ${stockError}
        </div>
        <br>


        <button type="submit" class="btn btn-success">Add</button>
    </form:form>
    <br>
    <a href="/" class="btn btn-primary btn-sm">Main</a>
</div>
</body>
</html>