<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; }
        .container { width: 300px; margin: 40px auto; padding: 20px; background-color: #fff; border: 1px solid #ddd; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
        .form-label { display: block; margin-bottom: 10px; }
        .form-input { width: 100%; padding: 10px; margin-bottom: 20px; border: 1px solid #ccc; }
        .form-input:focus { border-color: #aaa; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
        .submit-button { background-color: #4CAF50; color: #fff; padding: 10px 20px; border: none; border-radius: 10px; cursor: pointer; }
        .submit-button:hover { background-color: #3e8e41; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Reset Password</h2>
        <form action="${pageContext.request.contextPath}/ResetPasswordServlet" method="post">
            <label class="form-label" for="account_no">Account Number:</label>
            <input class="form-input" type="text" id="account_no" name="account_no" required>
            
            <label class="form-label" for="new-password">New Password:</label>
            <input class="form-input" type="password" id="new-password" name="new-password" required>
            
            <label class="form-label" for="confirm-password">Confirm Password:</label>
            <input class="form-input" type="password" id="confirm-password" name="confirm-password" required>
            
            <input class="submit-button" type="submit" value="Reset Password">
        </form>
    </div>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <script>
            alert('<%= message %>');
        </script>
    <%
        }
    %>
</body>
</html>
