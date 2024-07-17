<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Customer</title>
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
            color: #C0392B; /* Red heading color */
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
        input[type="text"],
        input[type="email"],
        input[type="number"],
        input[type="date"],
        input[type="password"],
        select {
            width: 100%; /* Full width inputs */
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc; /* Light border */
            border-radius: 4px; /* Rounded corners */
            font-size: 16px; /* Larger text for inputs */
        }
        button {
            padding: 10px;
            border: none;
            border-radius: 4px; /* Rounded corners */
            background-color: #C0392B; /* Button color */
            color: white; /* Button text color */
            font-size: 16px;
            cursor: pointer; /* Pointer cursor */
            margin-bottom: 20px; /* Space below button */
        }
        button:hover {
            background-color: #A93226; /* Darker red on hover */
        }
        input[type="submit"] {
            background-color: #007BFF; /* Submit button color */
            border: none; /* Remove border */
            color: white; /* Text color */
            cursor: pointer; /* Pointer cursor */
        }
        input[type="submit"]:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Register Customer</h2>
        <form action="register" method="post">
            <label for="cus_id">Customer ID:</label>
            <input type="text" id="cus_id" name="cus_id" required>
            <button type="button" onclick="generateCustomerId()">Generate Customer ID</button><br>

            <label for="full_name">Full Name:</label>
            <input type="text" id="full_name" name="full_name" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>

            <label for="mobile_no">Mobile No:</label>
            <input type="text" id="mobile_no" name="mobile_no" required>

            <label for="email_id">Email ID:</label>
            <input type="email" id="email_id" name="email_id" required>

            <label for="account_type">Account Type:</label>
            <select id="account_type" name="account_type">
                <option value="Saving">Saving</option>
                <option value="Current">Current</option>
            </select>

            <label for="initial_balance">Initial Balance:</label>
            <input type="number" id="initial_balance" name="initial_balance" min="1000" required>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <label for="id_proof">ID Proof:</label>
            <input type="text" id="id_proof" name="id_proof" required>

            <label for="Account_Number">Account Number:</label>
            <input type="text" id="Account_Number" name="Account_Number" required>
            <button type="button" onclick="generateAccountNumber()">Generate Account Number</button><br>

            <label for="Password">Password:</label>
            <input type="password" id="Password" name="Password" required>
            <button type="button" onclick="generateAccountInfo()">Generate Account Info</button><br>

            <label for="account-status">Account Status:</label>
            <select id="account-status" name="account-status">
                <option value="Active">Active</option>
                <option value="Closed">Closed</option>
            </select>

            <input type="submit" value="Register">
        </form>
    </div>

    <script>
        function generateCustomerId() {
            const randomNumber = Math.floor(10000 + Math.random() * 90000);
            document.getElementById("cus_id").value = randomNumber;
        }

        function generateAccountNumber() {
            const randomNumber = Math.floor(100000000000 + Math.random() * 900000000000);
            document.getElementById("Account_Number").value = randomNumber;
        }

        function generateAccountInfo() {
            const password = generatePassword();
            document.getElementById("Password").value = password;
        }

        function generatePassword() {
            const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            let password = "";
            for (let i = 0; i < 12; i++) {
                password += characters.charAt(Math.floor(Math.random() * characters.length));
            }
            return password;
        }
    </script>
</body>
</html>
