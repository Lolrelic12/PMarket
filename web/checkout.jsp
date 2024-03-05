<%-- 
    Document   : checkout
    Created on : 1 Mar 2024, 19:42:27
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<%@page import="dal.CartDAO"%>
<%@page import="model.Cart"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
            CartDAO cd = new CartDAO();
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
        <%}%>

        <h2>Place order</h2>
        <table style="border-style: none">
            <tr>
                <th>No.</th>
                <th></th>
                <th>Name</th>
                <th>Price</th>
                <th>Count</th>
                <th>Total</th>
            </tr>
            <%
                List<Product> productList = (List<Product>)cd.getCartItems(a.getAccountId());
                if (productList != null && productList.size() > 0) {
                int i = 1;
                for (Product p: productList) {
            %>
            <tr>
                <td><%= i %></td>
                <td><a href="productdetails?id=<%= p.getProductId() %>"><img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60"></a></td> 
                <td><a href="productdetails?id=<%= p.getProductId() %>"><%= p.getName() %></a></td> 
                <td><%= p.getPrice() %> VND</td>
                <td><%= p.getStock() %></td>
                <td><%= p.getPrice() * p.getStock() %> VND</td>
            </tr>
            <%};
                i++;
            } else {%>
            <p>Shopping cart is currently empty.</p>
            <%}%>
        </table>
        <%
                //List<Student> studentList = data;
                if (productList.size() > 0) {
            %>
        <p>Grand total: <%= cd.getCartTotal(a.getAccountId()) %> VND</p>
        <p>Your balance: <%= a.getBalance() %> VND</p>
        
        <% if (cd.getCartTotal(a.getAccountId()) <= a.getBalance()) { %>
        <button type="button" onclick="location.href='purchase?userid=<%= a.getAccountId() %>'">Confirm purchase</button>
        <%} else {%> 
        <p>Insufficient funds. Please <a href="topup.jsp">top-up your balance</a> or remove some items to continue with checkout.</p>
        <%}}%>
    </body>
</html>
