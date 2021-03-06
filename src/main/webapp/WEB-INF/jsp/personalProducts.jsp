<%@ page import="md.cernev.ProductManagment.utils.Utils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Magazine</title>
    <link href="<c:url value='/static/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/app.css' />" rel="stylesheet"></link>
</head>

<body>

<div class="generic-container">
    <h3><span class="label lavel-warning">${error}</span></h3>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">My products</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>

                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${user.products}" var="product">
                <tr>
                    <td>${product.product.name}</td>
                    <td>${product.quantity}</td>
                    <td>${product.sailingPrice}</td>
                    <td>
                        <c:choose>
                        <c:when test="${product.quantity>0}">
                        <a href="<c:url value='/sell/${user.id}/${product.product.id}'/>" class="btn btn-danger"
                           data-toggle="tooltip" title="Warning!The selling price is lower than actual.">Sell</a>
                        </c:when>
                        <c:otherwise>
                        <a href="<c:url value='/sell/${user.id}/${product.product.id}'/>" class="btn disabled" disabled>Sell</a>
                        </c:otherwise>
                        </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">All products</span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th width="25">Creation time</th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.stock}</td>
                    <td>${product.creationTime}</td>
                    <td>
                        <c:choose>
                            <c:when test="${product.stock>0}">
                                <a href="<c:url value='/buy/${user.id}/${product.id}'/>" class="btn btn-success">Buy</a>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value='/buy/${user.id}/${product.id}'/>" class="btn disabled" disabled>Buy</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h2><span class="label label-default">Wallet:${user.wallet}</span></h2>
    <a class="btn btn-success" href="/product">Add New Product</a>
    <a href="/" class="btn btn-primary btn-sm">Main</a>


</div>
</body>
</html>