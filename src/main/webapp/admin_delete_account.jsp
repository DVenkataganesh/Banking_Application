<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Customer Account</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* Light background */
            color: #333; /* Dark text */
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #C0392B; /* Red heading color */
            text-align: center;
        }
        .container {
            max-width: 400px; /* Set max width for the form */
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
        input[type="text"] {
            width: 100%; /* Full width inputs */
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc; /* Light border */
            border-radius: 4px; /* Rounded corners */
            font-size: 16px; /* Larger text for inputs */
        }
        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px; /* Rounded corners */
            background-color: #C0392B; /* Button color */
            color: white; /* Button text color */
            font-size: 16px;
            cursor: pointer; /* Pointer cursor */
        }
        button:hover {
            background-color: #A93226; /* Darker red on hover */
        }
        a {
            display: block; /* Block display for link */
            text-align: center; /* Center align */
            margin-top: 20px; /* Spacing above the link */
            color: #007BFF; /* Link color */
            text-decoration: none; /* Remove underline */
        }
        a:hover {
            text-decoration: underline; /* Underline on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Delete Customer Account</h1>
        <form action="adminDeleteAccount" method="post">
            <label for="account_no">Enter Account Number:</label>
            <input type="text" id="account_no" name="account_no" required>
            <button type="submit">Submit</button>
        </form>
        
        <a href="admindashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
