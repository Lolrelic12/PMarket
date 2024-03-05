<%-- 
    Document   : login
    Created on : 1 Mar 2024, 19:30:26
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <h2>Management console</h2>
        <form action="adminlogin" method="post">
            <label for="usernameField">Username:</label> <input type="text" id="usernameField" name="username" required="required"><br>
            <label for="passwordField">Password:</label> <input type="password" id="passwordField" name="password" required="required"><br>
            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Login</button>
        </form>
    </body>
</html>
