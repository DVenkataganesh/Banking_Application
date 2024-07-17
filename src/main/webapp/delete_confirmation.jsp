<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Account Confirmation</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <div class="container">
        <h1>Delete Account Confirmation</h1>
        <p>Are you sure you want to delete the account with account number: <%= request.getAttribute("account_no") %>?</p>
        <p>Current Balance: <%= request.getAttribute("balance") %></p>
        
        <form action="performDeleteAccount" method="post">
            <input type="hidden" name="account_no" value="<%= request.getAttribute("account_no") %>">
            <button type="submit">Yes, Delete Account</button>
        </form>
        
        <a href="admindashboard.jsp">Cancel</a>
    </div>
</body>
</html>
