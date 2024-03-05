<%-- 
    Document   : account
    Created on : 2 Mar 2024, 11:51:45
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
        <title>My PMarket account</title>
        <script src="js/script.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
                List<Product> productList = (List<Product>)request.getAttribute("data");
                String userId = (String)session.getAttribute("userid");
                Account a = new Account();
                
                if (userId != null && !userId.equals("administrator")) {
                        a = ad.getAccountById(Integer.parseInt(userId));
        %>
        <p>Welcome, <a href="account.jsp"><%= a.getDisplayName() %></a></p>
        <a href="cart.jsp">My shopping cart</a><br>
        <a href="history.jsp">Recent purchases</a><br><br>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
        <h3><a href="home.jsp">< Back to home </a></h3>
        <% 
            } else {
        %>
        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <%};%>
        
        <p>Account info for account: <%= a.getUsername() %></p>
        
        <span>Account balance: <%= a.getBalance() %> VND</span><br>
        <a href="topup.jsp?userid=<%= a.getAccountId() %>">Topup your balance</a><br><br>
        
        <span>Display name: </span>
        <form action="changename" method="post">
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" value="<%= a.getUsername() %>">
            <label for="newEmailField">Display name: </label><input type="text" id="newNameField" name="newName" required="required" value="<%= a.getDisplayName() %>"><br>
            <button type="submit">Change display name</button>
        </form>

        <br><br><span>Change email: </span>
        <form action="changeemail" method="post">
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" value="<%= a.getUsername() %>">
            <label for="newEmailField">Email address: </label><input type="email" id="newEmailField" name="newEmail" required="required" value="<%= a.getEmail() %>"><br>
            <button type="submit">Change email</button>
        </form>
        <% 
            if(!a.isVerified()) {
        %>
        <span>Your email is not yet verified. Please verify your email to shop with PMarket and to ensure access to your account lest you forget your password.</span>
        <form action="verifyemail" method="get">
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" value="<%= a.getUsername() %>">
            <input type="email" id="emailField" name="email" required="required" value="<%= a.getEmail() %>" hidden="hidden">
            <button type="submit">Verify email</button>
        </form>
        <%
            } else {
        %>
        <span>Your email is verified.</span>
        <%}%>

        <br><br><span>Change password: </span>
        <form action="changepassword" method="post">
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" value="<%= a.getUsername() %>">
            <label for="passwordField">Old password: </label><input type="password" id="passwordField" name="password" required="required"><br>
            <label for="newPasswordField">New password: </label><input type="password" id="newPasswordField" name="newPassword" required="required"><br>
            <label for="confirmPasswordField">Confirm password: </label><input type="password" id="confirmPasswordField" name="confirmPassword" required="required"><br>
            <button type="submit">Change password</button> <p style="color : red">${requestScope.error}</p>
        </form>

        <button type="button" onclick="toggleElement('deleteForm')">Delete account</button>
        <form id="deleteForm" action="deleteaccount" method="post" style="display: none">
            <span>WARNING: You are about to delete your account and all related data. This action is irreversible!</span><br>
            <span>Enter your password to confirm account deletion:</span><br>
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" value="<%= a.getUsername() %>">
            <label for="passwordField">Enter password: </label><input type="password" id="passwordField" name="password" required="required"><br>
            <button type="submit">Confirm account deletion</button>
            </form>
    </body>
</html>
