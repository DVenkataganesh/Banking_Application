<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="bank.model.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Customer Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* Light background */
            color: #333; /* Dark text */
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #007BFF; /* Blue heading color */
            text-align: center;
        }
        .container {
            max-width: 600px; /* Set max width for the form */
            margin: auto;
            background-color: #fff; /* White background for the form */
            border-radius: 8px; /* Rounded corners */
            padding: 20px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }
        label {
            margin-bottom: 8px;
            display: block; /* Block display for labels */
        }
        input[type="text"], input[type="email"] {
            width: 100%; /* Full width inputs */
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc; /* Light border */
            border-radius: 4px; /* Rounded corners */
            font-size: 16px; /* Larger text for inputs */
        }
        input[type="submit"], button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px; /* Rounded corners */
            background-color: #007BFF; /* Button color */
            color: white; /* Button text color */
            font-size: 16px;
            cursor: pointer; /* Pointer cursor */
            margin-top: 10px; /* Spacing above buttons */
        }
        input[type="submit"]:hover, button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.all.min.js"></script>
</head>
<body>
    <div class="container">
        <h2>Modify Customer Details</h2>

        <%
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("admin_login.jsp");
            }
        %>

        <form action="fetchCustomer" method="post">
            <label for="account_no">Account Number:</label>
            <input type="text" id="account_no" name="account_no" required>
            <input type="submit" value="Fetch Details">
        </form>

        <%
            Customer customer = (Customer) request.getAttribute("customer");
            if (customer != null) {
        %>
            <form action="updateCustomer" method="post">
                <input type="hidden" name="account_no" value="<%= customer.getAccountNumber() %>">
                <label for="full_name">Full Name:</label>
                <input type="text" id="full_name" name="full_name" value="<%= customer.getFullName() %>" required>
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>
                <label for="mobile_no">Mobile No:</label>
                <input type="text" id="mobile_no" name="mobile_no" value="<%= customer.getMobileNumber() %>" required>
                <label for="email_id">Email ID:</label>
                <input type="email" id="email_id" name="email_id" value="<%= customer.getEmail() %>" required>
                <input type="submit" value="Update Customer">
            </form>
        <%
            }
        %>

        <button onclick="location.href='admindashboard.jsp'">Back to Admin Dashboard</button>

        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
                <script>
                    Swal.fire({
                        title: 'Update Result',
                        text: '<%= message %>',
                        icon: '<%= message.startsWith("Customer details updated") ? "success" : "error" %>'
                    });
                </script>
        <%
            }
        %>
    </div>
</body>
</html>
