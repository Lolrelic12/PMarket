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
    </head>
    <body>
        <form action="login" method="post">
            <label for="usernameField">Username:</label> <input type="text" id="usernameField" name="username" required="required"><br>
            <label for="passwordField">Password:</label> <input type="password" id="passwordField" name="password" required="required"><br>
            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Login</button>
            <button type="button" onclick="location.href = 'register.jsp?'">Register</button>
        </form>
    </body>
</html>
