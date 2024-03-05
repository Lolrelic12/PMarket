<%-- 
    Document   : verifyemail
    Created on : 3 Mar 2024, 13:42:45
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
        <title>Verify your email</title>
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

        <h3><a href="account.jsp">< Back to account</a></h3>
        <% 
            } else {
        %>

        <a href="login.jsp">Login</a>
        <a href="register.jsp">Register</a>
        <%};
        if (a == null) {
        
        %>
        <script>
            // Redirect to another JSP page
            window.location.href = "login.jsp";
        </script>
        <% 
            };
        %>
        <p>A 6-digit verification code has been sent to your email. Please enter the code below to complete the verification process: </p>
        
        <form action="verifyemail" method="post">
            <input type="text" id="usernameField" name="username" required="required" hidden="hidden" value="<%= a.getUsername() %>">
            <input type="text" name="template" hidden="hidden" required="required" value="<%= request.getAttribute("template") %>">
            <label for="responseField"><input type="text" name="response" id="responseField" required="required"></label>
            <p style="color : red">${requestScope.error}</p>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
