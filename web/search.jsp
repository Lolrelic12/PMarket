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
                List<Product> productList = pd.getAllProductsContaining(request.getParameter("token"));
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

        <h2>Search results for "<%= request.getParameter("token") %>"</h2>
        
        <h3><a href="home.jsp">< Back to all products </a></h3>

        <form action="search.jsp" method="get">
            <span><label for="searchField">Search: </label><input type="text" id="searchField" name="token" <%if (request.getParameter("token") != null) {%>value="<%= request.getParameter("token") %>"<%}%>>
                <button type="submit">Search</button></span>
        </form>
        <br>

        <table style="border-style: none">
            <tr>

            </tr>
            <%
                if (productList != null && productList.size() > 0) {
                for (Product p: productList) {
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

    </body>
</html>