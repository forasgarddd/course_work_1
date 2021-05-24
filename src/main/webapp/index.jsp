<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Join Us</title>
</head>
<body>
<div class="container">
    <div class="box">
        <h1>Login Account</h1>
        <form action="LoginServlet" method="post">
            <p>Email</p>
            <input type="text" placeholder="Email" name="email" required>
            <p>Password</p>
            <input type="password" placeholder="Password" name="password" required>
            <input type="submit" value="Login">
            <a href="registration.jsp">Create New Account</a>
        </form>
    </div>
</div>
</body>
</html>