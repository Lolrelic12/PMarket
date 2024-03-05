<%-- 
    Document   : productdetails
    Created on : 4 Mar 2024, 01:04:43
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<%@page import="jakarta.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
    <head>
        <%
                Product p = (Product)request.getAttribute("product");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PMarket</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
                List<Product> productList = (List<Product>)request.getAttribute("data");
                Cookie[] ck = request.getCookies();  
                String userId = (String)session.getAttribute("userid");
                
                if (userId != null && !userId.equals("administrator")) {
                        Account a = ad.getAccountById(Integer.parseInt(userId));
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
        <h3><a href="listitems">< Back to home </a></h3>
        <%}%>

        <h2><%= p.getName() %></h2>
        <img src="<%=p.getImageLink()%>" alt="product image" width="150" height="60">
        <p>Price: <%= p.getPrice() %> VND</p>
        <p> Category: <%= p.getCategory() %></p>
        <p> Description: <%= p.getDescription() %></p>

        <form action="addtocart" method="post">
            <input type="hidden" name="productId" value="<%= p.getProductId() %>">
            <label for="amountField">Select amount: </label><input type="number" id="amountField" name="amount" min="1" max="20" value="1">
            <button type="submit">Add to cart</button>
        </form>
    </body>
</html>
