<%-- 
    Document   : productdetails
    Created on : 4 Mar 2024, 01:04:43
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <%
                Product p = (Product)request.getAttribute("product");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PMarket</title>

    </head>
    <body>
        <h1><%= p.getName() %></h1>
        <img src="<%=p.getImageLink()%>" alt="product image" width="150" height="60">
        <p>Price: <%= p.getPrice() %> VND</p>
        <p>In stock: <%= p.getStock() %></p>
        <p> Category: <%= p.getCategory() %></p>
        <p> Description: <%= p.getDescription() %></p>

        <form action="addtocart" method="post">
            <input type="hidden" name="productId" value="<%= p.getProductId() %>">
            <label for="amountField">Select amount: </label><input type="number" id="amountField" name="amount" min="1" max="<%= p.getStock() %>" value="1">
            <button type="submit">Add to cart</button>
        </form>
    </body>
</html>
