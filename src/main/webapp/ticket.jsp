<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Ticket</title>
</head>

<body>


<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center">Your ticket: </h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Movie</th>
                <th>Showtime</th>
                <th>Price</th>
                <th>Place row</th>
                <th>Place column</th>
                <th>Print</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
                <tr>
                    <td>
                        <c:out value="${selectedMovie}" />
                    </td>
                    <td>
                        <c:out value="${selectedShowtime}" />
                    </td>
                    <td>
                        <c:out value="${selectedPrice}" />
                    </td>
                    <td>
                        <c:out value="${selectedPlaceRow}" />
                    </td>
                    <td>
                        <c:out value="${selectedPlaceColumn}" />
                    </td>
                    <td>
                        <a onclick="window.print();"><button class="button right-button">Print</button></a>
                    </td>
                </tr>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>