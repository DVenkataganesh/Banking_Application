<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="bank.model.Customer" %> <!-- Import the Customer class -->
    
<!DOCTYPE html>
<html>
<head>
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #e8f4f8; /* Lighter background for a fresh look */
            margin: 0;
            padding: 20px; /* Added padding for better spacing */
        }
        h2 {
            color: #004d66; /* Darker shade for contrast */
            text-align: center; /* Centered the title */
            font-size: 24px; /* Increased font size for better visibility */
            margin-bottom: 10px; /* Added margin for separation */
        }
        .customer-details {
            text-align: center; /* Center-aligns the details */
            margin: 20px 0; /* Margin for separation */
            padding: 15px; /* Added padding */
            background-color: #ffffff; /* White background for contrast */
            border-radius: 10px; /* Rounded corners */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Soft shadow effect */
            width: 80%; /* Adjusted width for better layout */
            margin: 20px auto; /* Centered the container */
        }
        ul {
            list-style: none;
            padding: 0;
            margin: 20px 0; /* Added margin for separation */
        }
        li {
            margin-bottom: 15px; /* Increased spacing between links */
        }
        a {
            text-decoration: none;
            color: #007acc; /* Changed link color for better visibility */
            font-weight: bold; /* Made links bold */
            padding: 10px 15px; /* Added padding for clickable area */
            border-radius: 5px; /* Rounded corners for buttons */
            transition: background-color 0.3s; /* Smooth transition for hover effect */
            display: block; /* Makes the whole area clickable */
            text-align: center; /* Center text in links */
            background-color: #f7f7f7; /* Added background color for buttons */
            border: 1px solid #ccc; /* Added border for buttons */
        }
        a:hover {
            background-color: #0099cc; /* Background color change on hover */
            color: white; /* Text color on hover */
        }
        .error {
            color: red;
            font-weight: bold;
            text-align: center; /* Centered error message */
            margin-top: 20px; /* Added margin for spacing */
        }
        /* Responsive adjustments */
        @media (max-width: 600px) {
            body {
                padding: 10px; /* Reduced padding for smaller screens */
            }
            h2 {
                font-size: 1.5em; /* Adjusted font size for smaller screens */
            }
            li {
                margin-bottom: 10px; /* Reduced spacing for smaller screens */
            }
            .customer-details {
                width: 90%; /* Adjusted width for smaller screens */
            }
        }
    </style>
</head>
<body>
    <h2>Customer Dashboard</h2>
    
    <% 
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
    %>
        <div class="customer-details">
            <p>Account No: <%= customer.getAccountNumber() %></p>
            <p>Balance: <%= customer.getInitialBalance() %></p>
            <p>Full Name: <%= customer.getFullName() %></p>
            <p>Email ID: <%= customer.getEmail() %></p>
            <p>Account Type: <%= customer.getTypeOfAccount() %></p>
        </div>
    <% 
        } else {
    %>
        <p>Customer information not available.</p>
    <% 
        }
    %>

    <h3>Actions</h3>
    <ul>
        <li><a href="transactionHistory">View Transactions</a></li>
        <li><a href="Deposit.jsp">Deposit</a></li>
        <li><a href="withdraw.jsp">Withdraw</a></li>
        <li><a href="closingacccount.jsp">Close Account</a></li>
        <li><a href="logout">Log Out Account</a></li>
    </ul>

    <p class="error">
        <%= request.getParameter("error") != null ? request.getParameter("error") : "" %>
    </p>
</body>
</html>