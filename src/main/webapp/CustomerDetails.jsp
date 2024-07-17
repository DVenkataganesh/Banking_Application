<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bank.model.Customer" %> <!-- Import the Customer class -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Customer Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <div class="container">
        <h1>All Customer Details</h1>
        <table border="1">
            <tr>
                <th>Customer ID</th>
                <th>Account Number</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Address</th>
                <th>Initial Balance</th>
                <th>Account Type</th>
                <th>Date of Birth</th>
                <th>ID Proof</th>
                <th>Status</th>
            </tr>
            <%
                List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                if (customers != null) {
                    for (Customer customer : customers) {
            %>
            <tr>
                <td><%= customer.getCustomerId() %></td>
                <td><%= customer.getAccountNumber() %></td>
                <td><%= customer.getFullName() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getMobileNumber() %></td>
                <td><%= customer.getAddress() %></td>
                <td><%= customer.getInitialBalance() %></td>
                <td><%= customer.getTypeOfAccount() %></td>
                <td><%= customer.getDateOfBirth() %></td>
                <td><%= customer.getIdProof() %></td>
                <td><%= customer.getStatus() %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <a href="admindashboard.jsp">Back to Dashboard</a>
    </div>
</body>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #1a1a1a; /* Dark background */
    color: white; /* White text for contrast */
    margin: 0;
    padding: 20px;
}

.container {
    max-width: 1200px;
    margin: auto;
    background-color: #2c2c2c; /* Slightly lighter background for the container */
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
}

h1 {
    color: #00bcd4; /* Bright color for the heading */
    text-align: center;
}

table {
    width: 100%;
    border-collapse: collapse; /* Remove space between borders */
    margin-top: 20px;
}

th, td {
    padding: 12px; /* Add padding for table cells */
    border: 1px solid #444; /* Darker border for cells */
    text-align: left; /* Align text to the left */
}

th {
    background-color: #00bcd4; /* Header background color */
    color: white; /* Header text color */
}

tr:nth-child(even) {
    background-color: #3c3c3c; /* Alternate row color */
}

tr:hover {
    background-color: #555; /* Highlight row on hover */
}

a {
    display: block; /* Make link a block element */
    margin-top: 20px;
    text-align: center; /* Center align link */
    color: #00bcd4; /* Link color */
    text-decoration: none; /* Remove underline */
}

a:hover {
    color: #0097a7; /* Darker link color on hover */
}

</style>
</html>
