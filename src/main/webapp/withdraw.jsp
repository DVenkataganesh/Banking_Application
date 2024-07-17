<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Withdraw</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4; /* Light background */
            color: #333; /* Dark text */
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 400px; /* Set max width for the form */
            margin: auto;
            background-color: #fff; /* White background for the form */
            border-radius: 8px; /* Rounded corners */
            padding: 20px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
        }
        h2 {
            color: #007BFF; /* Blue heading color */
            text-align: center;
        }
        label {
            margin-bottom: 8px;
            display: block; /* Block display for labels */
        }
        input[type="text"], input[type="number"] {
            width: 100%; /* Full width inputs */
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc; /* Light border */
            border-radius: 4px; /* Rounded corners */
            font-size: 16px; /* Larger text for inputs */
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px; /* Rounded corners */
            background-color: #007BFF; /* Button color */
            color: white; /* Button text color */
            font-size: 16px;
            cursor: pointer; /* Pointer cursor */
        }
        input[type="submit"]:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Withdraw Funds</h2>
        <form action="withdraw" method="post">
            <label for="accountNumber">Account Number:</label>
            <input type="text" id="accountNumber" name="accountNumber" required>
            <label for="amount">Withdrawal Amount:</label>
            <input type="number" id="amount" name="amount" required>
            <input type="submit" value="Withdraw">
        </form>
    </div>
</body>
</html>
