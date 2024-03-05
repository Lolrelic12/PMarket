<%-- 
    Document   : topup
    Created on : 1 Mar 2024, 19:42:33
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="model.Account"%>
<%@page import="dal.AccountDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top-up your PMarket balance</title>
        <script src="js/script.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/stylesheet.css">
    </head>
    <body>
        <h1><a href="home.jsp">PMarket</a></h1>
        <%
            AccountDAO ad = new AccountDAO();
                List<Product> productList = (List<Product>)request.getAttribute("data");
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
        <h3><a href="account.jsp">< Back to account </a></h3>
        <% 
            } else {
        %>

        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <%};%>
        
        <h2>Top-up your PMarket balance</h2>
        
        <span>Select the amount you want to top-up below and click Confirm. You will be given a QR code.</span><br>
        <span>After having completed the bank transfer, your requested amount of funds will be added to your balance.</span><br><br>
        
        <form action="topup" method="post">
            <input type="hidden" name="userid" value="<%= a.getAccountId()%>">
            <label for="amountField">Topup amount: </label>
            <select id="amountField" name="amount" required="required">
                <option value="10000">10.000 VND</option>
                <option value="20000">20.000 VND</option>
                <option value="50000">50.000 VND</option>
                <option value="100000">100.000 VND</option>
                <option value="200000">200.000 VND</option>
                <option value="500000">500.000 VND</option>
                <option value="1000000">1.000.000 VND</option>
                <option value="2000000">2.000.000 VND</option>
                <option value="5000000">5.000.000 VND</option>
            </select>
            <button type="button" onclick="toggleElement('qr')">Confirm</button><br><br>
            <div id="qr" style="display: none">
               <span>Please transfer the appropriate amount to the following bank account with the message "PMARKET_TOPUP_<%= a.getAccountId() %>"</span><br>
               <img src="img/qr.jpg" alt="alt" width="250"/><br><br>
               <button type="submit">I have completed the bank transfer</button><br><br>
               <span>Your account balance will be updated shortly.</span>
            </div>
        </form>
        
    </body>
</html>
