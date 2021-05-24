<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Show management</title>
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark">
        <div>
            <a class="navbar-brand"> Show Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Shows</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Shows</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Show</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Movie</th>
                <th>Showtime</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="show" items="${listShow}">
                <tr>
                    <td>
                        <c:out value="${show.id}" />
                    </td>
                    <td>
                        <c:out value="${show.movie}" />
                    </td>
                    <td>
                        <c:out value="${show.showtime}" />
                    </td>
                    <td>
                        <c:out value="${show.price}" />
                    </td>
                    <td><a href="edit?id=<c:out value='${show.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${show.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>