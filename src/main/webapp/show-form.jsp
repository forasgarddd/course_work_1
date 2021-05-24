<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Show Management Application</title>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${show != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${show == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${show != null}">
                                Edit Show
                            </c:if>
                            <c:if test="${show == null}">
                                Add New Show
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${show != null}">
                        <input type="hidden" name="id" value="<c:out value='${show.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Show Movie</label> <input type="text" value="<c:out value='${show.movie}' />" class="form-control" name="movie" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Show showtime</label> <input type="datetime-local" value="<c:out value='${show.showtime}' />" class="form-control" name="showtime">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Show price</label> <input type="text" value="<c:out value='${show.price}' />" class="form-control" name="price">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>