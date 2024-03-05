<%-- 
    Document   : forgotpassword
    Created on : 5 Mar 2024, 21:33:04
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account recovery</title>
    </head>
    <body>
        <% String verificationCode = (String)request.getAttribute("template"); %>
        <h1><a href="home.jsp">PMarket</a></h1>
        <h3><a href="login.jsp">< Back to login </a></h3>
        
        <h2>Account recovery</h2>
        <% if (verificationCode == null || verificationCode.equals("null")) { %>
        <p>Please enter the email address you used for your account to proceed to resetting your password:</p>
        <form action="resetpassword" method="get">
            <label for="emailField">Email: </label><input type="email" id="emailField" name="email" required="required">
            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Submit</button>
        </form>
        <% } else { %>
        <p>A 6-digit verification code has been sent to your email. Please enter the code below to continue: </p>
        <form action="resetpassword" method="post">
            <input type="text" id="useridField" name="userid" required="required" hidden="hidden" value="<%= request.getAttribute("userid") %>">
            <input type="text" name="template" hidden="hidden" required="required" value="<%= request.getAttribute("template") %>">
            <label for="responseField"><input type="text" name="response" id="responseField" required="required"></label>
            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Submit</button>
        </form>
        <% } %>
    </body>
</html>
