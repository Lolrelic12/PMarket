<%-- 
    Document   : home
    Created on : 1 Mar 2024, 19:26:56
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PMarket</title>
    </head>
    <body>
        <%
            AccountDAO ad = new AccountDAO();
                List<Product> productList = (List<Product>)request.getAttribute("data");
                Cookie[] ck = request.getCookies();  
                String userId = (String)session.getAttribute("userid");
                
                if (userId != null) {
                        Account a = ad.getAccountById(Integer.parseInt(userId));
        %>
        <p>Welcome, <a href="account.jsp?userid=<%= a.getAccountId() %>"><%= a.getDisplayName() %></a></p>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
        <% 
            } else {
        %>

        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <%}%>

        <table style="border-style: none">
            <tr>

            </tr>
            <%
                //List<Student> studentList = data;
                if (productList != null) {
                for (Product p: productList) {
            %>
            <tr>
                <td><img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60"></td> 
                <td><a href="productdetails?id=<%= p.getProductId() %>"><%= p.getName() %></a></td> 
                <td><%= p.getDescription() %></td>
                <td><%= p.getPrice() %> VND</td>
                <td><%= p.getStock() %></td>
            </tr>
            <%}}%>
        </table>
    </body>
</html>
