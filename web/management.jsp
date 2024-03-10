<%-- 
    Document   : management
    Created on : 3 Mar 2024, 14:19:36
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
        <title>PMarket Management Console</title>
        <link rel="stylesheet" href="css/stylesheet.css">
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
            ProductDAO pd = new ProductDAO();
            
            ArrayList<Product> productList = pd.getAllProducts();
            ArrayList<Account> accountList = ad.getAllAccounts();
            
        %>
        <h2>PMarket Management Console</h2>
        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form><br>
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'products')" id="defaultOpen">Products</button>
            <button class="tablinks" onclick="openTab(event, 'accounts')">Accounts</button>
        </div>

        <div id="products" class="tabcontent">

            <form action="addproduct" method="post">
                <label for="nameField">Name: </label><input type="text" id="nameField" name="name" required="required">
                <label for="descriptionField">Description: </label><input type="text" id="descriptionField" name="description" required="required">
                <label for="imageLinkField">Image link: </label><input type="text" id="imageLinkField" name="imagelink" required="required">
                <label for="priceField">Price: </label><input type="number" id="priceField" name="price" required="required" min="0">
                <label for="categoryField">Category: </label>
                <select id="categoryField" name="category" required="required">
                    <option value="1">Steam Wallet Code</option>
                    <option value="2">Steam Gift Cards</option>
                </select>               
                <button type="submit">Add product</button>
            </form><br>

            <table style="border-style: none">
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <%
                    if (productList != null && productList.size() > 0) {
                    for (Product p: productList) {
                %>
                <tr>
                    <td><%= p.getProductId() %></td> 
                    <td><img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60"></td>
                    <td><%= p.getName() %></td>
                    <td><%= p.getCategory() %></td>
                    <td><%= p.getPrice() %></td>
                    <td><a href="editproduct.jsp?productid=<%= p.getProductId() %>">Details</a></td>
                </tr>
                <%}} else {%>
                <p>No result found</p>
                <%}%>
            </table>
        </div>

        <div id="accounts" class="tabcontent">
            <table style="border-style: none">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Display name</th>
                    <th>Email</th>
                    <th>Verified</th>
                    <th>Balance</th>
                    <th>Action</th>
                </tr>
                <%
                    if (accountList != null && accountList.size() > 0) {
                    for (Account a: accountList) {
                %>
                <tr>
                    <td><%= a.getAccountId() %></td> 
                    <td><%= a.getUsername() %></td> 
                    <td><%= a.getDisplayName() %></td>
                    <td><%= a.getEmail() %></td>
                    <td><%= a.isVerified() %></td>
                    <td><%= a.getBalance() %></td>
                    <td><a href="deleteaccount?accountid=<%= a.getAccountId() %>">Delete</a></td>
                </tr>
                <%}}%>
            </table>
        </div>

        <script>
            document.getElementById("defaultOpen").click();
        </script>
    </body>
</html>
