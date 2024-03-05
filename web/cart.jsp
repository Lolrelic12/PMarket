<%-- 
    Document   : cart
    Created on : 1 Mar 2024, 19:28:19
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
        <title>My shopping cart</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
            CartDAO cd = new CartDAO();
            String userId = (String)session.getAttribute("userid");
                List<Product> productList = cd.getCartItems(Integer.parseInt(userId));
                float total = (Float)request.getAttribute("total");
                
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
        <%}%>

        <h2>Shopping cart</h2>
        <table style="border-style: none">
            <tr>
                <th>No.</th>
                <th></th>
                <th>Name</th>
                <th>Price</th>
                <th>Count</th>
                <th>Action</th>
            </tr>
            <%
                //List<Student> studentList = data;
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
                <td><a href="removefromcart?userid=<%= a.getAccountId() %>&productid=<%= p.getProductId() %>">Remove</a></td>
            </tr>
            <%};
                i++;
            } else {%>
            <p>Your shopping cart is currently empty.</p>
            <%}%>
        </table><br>
        <%
                //List<Student> studentList = data;
                if (productList.size() > 0) {
            %>
        <button type="button" onclick="location.href='checkout.jsp?userid=<%= a.getAccountId() %>'">Proceed to checkout</button>
        <%}%>
    </body>
</html>
