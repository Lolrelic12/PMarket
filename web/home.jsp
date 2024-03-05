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
<%@page import="dal.ProductDAO"%>
<%@page import="jakarta.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PMarket</title>
        <link rel="stylesheet" href="css/stylesheet.css">
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>

        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
            ProductDAO pd = new ProductDAO();
                List<Product> gameCodes = pd.getAllProductsOfCategory(2);
                List<Product> walletCodes = pd.getAllProductsOfCategory(1);
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
        <% 
            } else {
        %>

        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <%}%>


        <h2>All products</h2>

        <form action="search.jsp" method="get">
            <span><label for="searchField">Search: </label><input type="text" id="searchField" name="token">
                <button type="submit">Search</button></span>
        </form>
        <br>

        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'games')" id="defaultOpen">Steam Gift Cards</button>
            <button class="tablinks" onclick="openTab(event, 'wallet')">Steam Wallet Codes</button>
        </div>

        <div id="games" class="tabcontent">
            <table style="border-style: none">
                <tr>
                    
                </tr>
                <%
                    if (gameCodes != null && gameCodes.size() > 0) {
                    for (Product p: gameCodes) {
                %>
                <tr>
                    <td><a href="productdetails?id=<%= p.getProductId() %>"><img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60"></a></td> 
                    <td><a href="productdetails?id=<%= p.getProductId() %>"><%= p.getName() %></a></td> 
                    <td><%= p.getPrice() %> VND</td>
                </tr>
                <%}} else {%>
                <p>No result found</p>
                <%}%>
            </table>
        </div>

        <div id="wallet" class="tabcontent">
            <table style="border-style: none">
                <tr>
                    
                </tr>
                <%
                    if (walletCodes != null && walletCodes.size() > 0) {
                    for (Product p: walletCodes) {
                %>
                <tr>
                    <td><a href="productdetails?id=<%= p.getProductId() %>"><img src="<%= p.getImageLink() %>" alt="<%= p.getName() %>" width="150" height="60"></a></td> 
                    <td><a href="productdetails?id=<%= p.getProductId() %>"><%= p.getName() %></a></td> 
                    <td><%= p.getPrice() %> VND</td>
                </tr>
                <%}} else {%>
                <p>No result found</p>
                <%}%>
            </table>
        </div>

        <script>
            document.getElementById("defaultOpen").click();
        </script>

    </body>
</html>
