<%-- 
    Document   : history
    Created on : 1 Mar 2024, 19:58:10
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<%@page import="dal.ProductDAO"%>
<%@page import="dal.PurchaseHistoryDAO"%>
<%@page import="model.Cart"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My purchases</title>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
            PurchaseHistoryDAO phd = new PurchaseHistoryDAO();
            ProductDAO pd = new ProductDAO();
            String userId = (String)session.getAttribute("userid");
                HashMap<Integer, LocalDate> history = phd.getHistory(Integer.parseInt(userId));
                
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

        <h2>Recent purchases</h2>

        <table style="border-style: none">
            <tr>
                <th>No.</th>
                <th></th>
                <th>Product name</th>
                <th>Date purchased</th>
                <th>Action</th>
            </tr>
            <%
                //List<Student> studentList = data;
                if (history != null && history.size() > 0) {
                    int productId = 0;
                    LocalDate dateBought = null;
                    int i = 1;
                    for (Map.Entry<Integer, LocalDate> entry : history.entrySet()) {
                        productId = (int)entry.getKey();
                        Product p = pd.getProduct(productId);
                        dateBought = (LocalDate)entry.getValue();
            %>
            <tr>
                <td><%= i %></td>
                <td><img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60"></td> 
                <td><%= p.getName() %></td> 
                <td><%= dateBought %></td>
                <td><a href="productdetails?id=<%= p.getProductId() %>">Buy again</a></td>
            </tr>
            <%i++;}} %>
        </table><br>

    </body>
</html>
