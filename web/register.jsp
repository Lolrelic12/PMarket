<%-- 
    Document   : register
    Created on : 1 Mar 2024, 19:30:36
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <script src="script.js"></script>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <h3><a href="home.jsp">< Back to home </a></h3>
        <h2>Create a PMarket account</h2>
        <form action="register" method="post" onsubmit="return verifyPasswords()">
            <label for="nameField">Full name:</label> <input type="text" id="nameField" name="name" required="required"><br>
            <label for="emailField">Email:</label> <input type="email" id="emailField" name="email" required="required"><br>
            <label for="usernameField">Username:</label> <input type="text" id="usernameField" name="username" required="required"><br>
            <label for="passwordField">Password:</label> <input type="password" id="passwordField" name="password" required="required"><br>
            <label for="confirmPasswordField">Confirm password:</label> <input type="password" id="confirmPasswordField" name="confirmPassword" required="required"><br>

            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Register</button>
            <p>Already have a PMarket account? <a href="login.jsp">Login</a></p>

        </form>
    </body>
</html>
