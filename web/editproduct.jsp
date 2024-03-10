<%-- 
    Document   : editproduct.jsp
    Created on : 10 Mar 2024, 21:46:47
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<%@page import="dal.ProductDAO"%>
<%@page import="jakarta.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit product</title>
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
            ProductDAO pd = new ProductDAO();
            
            ArrayList<Product> productList = pd.getAllProducts();
            ArrayList<Account> accountList = ad.getAllAccounts();
            
            int productId = Integer.parseInt(request.getParameter("productid"));
            Product p = pd.getProduct(productId);
            
        %>
        <h2>PMarket Management Console</h2>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form><br>
        <h3><a href="management.jsp">< Back to management console </a></h3>
        
        <p>Details for <%= p.getName() %></p>
        
        <img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60">

        <form action="updateproduct" method="post">
            <input type="number" id="idField" name="id" required="required" value="<%= p.getProductId() %>" hidden="hidden"><br>
            <label for="nameField">Name: </label><input type="text" id="nameField" name="name" required="required" value="<%= p.getName() %>"><br>
            <label for="descriptionField">Description: </label><input type="text" id="descriptionField" name="description" required="required" value="<%= p.getDescription() %>"><br>
            <label for="imageLinkField">Image link: </label><input type="text" id="imageLinkField" name="imagelink" required="required" value="<%= p.getImageLink() %>"><br>
            <label for="priceField">Price: </label><input type="number" id="priceField" name="price" required="required" min="0" value="<%= p.getPrice() %>"><br>
            <label for="categoryField">Category: </label>
            <select id="categoryField" name="category" required="required">
                <option value="1">Steam Wallet Code</option>
                <option value="2">Steam Gift Cards</option>
            </select><br>        
            <button type="submit">Save</button>
        </form><br>
        <button onclick="location.href='deleteproduct?productid=<%= p.getProductId() %>'" type="button">Delete product</button>

    </body>
</html>
