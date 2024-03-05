<%-- 
    Document   : unverifiedemail
    Created on : 5 Mar 2024, 14:03:46
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
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
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
        <h3><a href="cart.jsp">< Back to cart </a></h3>
        <% 
            } else {
        %>

        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <%};%>
        
        <p>Error: Your email <%= a.getEmail() %> is not yet verified.</p>
        <p>Please go to your profile and verify your email to be able to purchase and receive goods from PMarket.</p>
        <a href="account.jsp">To my profile</a>
        
    </body>
</html>
