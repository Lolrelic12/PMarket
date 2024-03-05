<%-- 
    Document   : resetpassword
    Created on : 6 Mar 2024, 00:25:33
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <h3><a href="login.jsp">< Back to login </a></h3>
        
        <h2>Reset password</h2>
        <form action="changepassword" method="get">
            <input type="text" id="userideField" name="userid" required="required" hidden="hidden" value="<%= request.getParameter("userid") %>">
            <label for="newPasswordField">New password: </label><input type="password" id="newPasswordField" name="newPassword" required="required"><br>
            <label for="confirmPasswordField">Confirm password: </label><input type="password" id="confirmPasswordField" name="confirmPassword" required="required"><br>
            <button type="submit">Change password</button> <p style="color : red">${requestScope.error}</p>
        </form>
    </body>
</html>
