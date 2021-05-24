<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Show</th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->

                <c:forEach var="show" items="${listShow}">
                    <form action="seat" method="post">
                <tr>
                    <td>
                        <input type="text" name="id" value="<c:out value="${show.id}"/>" readonly>


                        <input type="text" name="movie" value="<c:out value="${show.movie}"/>" readonly>


                        <input type="text" name="showtime" value="<c:out value="${show.showtime}"/>" readonly>


                        <input type="text" name="price" value="<c:out value="${show.price}"/>" readonly>

                        <br/><br/>

                        Select a Row:&nbsp;
                        <select name="row">
                            <c:forEach items="${listRows}" var="row">
                                <option value="${row}"
                                        <c:if test="${row eq selectedPlaceRow}">selected="selected"</c:if>
                                >Row: ${row}</option>
                            </c:forEach>
                        </select>
                        <br/><br/>
                        Select a Column:&nbsp;
                        <select name="column">
                            <c:forEach items="${listColumns}" var="column">
                                <option value="${column}"
                                        <c:if test="${column eq selectedPlaceColumn}">selected="selected"</c:if>
                                >Column: ${column}</option>
                            </c:forEach>
                        </select>
                        <br/><br/>
                        <input type="submit" value="Choose ticket">
                        <hr>
                    </td>
                </tr>
            </form>

            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>