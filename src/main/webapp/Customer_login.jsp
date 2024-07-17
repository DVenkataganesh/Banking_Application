<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
 <div align="center">
  <h1>Customer Login</h1>
  <form action="<%=request.getContextPath()%>/customerloginn" method="post">
   <table>
    <tr>
     <td>Account Number</td>
     <td><input type="text" name="account_no" required /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" required /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
  <%
  String error = (String) request.getAttribute("error");
  if (error != null && !error.isEmpty()) {
  %>
  <script>
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: '<%= error %>',
    });
  </script>
  <%
  }
  %>
 </div>
</body>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f8ff;
    margin: 0;
    padding: 20px;
}

h1 {
    color: #00698f;
    margin-bottom: 20px;
}

form {
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
    max-width: 400px;
    margin: auto;
}

table {
    width: 100%;
}

td {
    padding: 10px;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="submit"] {
    background-color: #00698f;
    color: white;
    border: none;
    padding: 10px;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
    font-size: 16px;
}

input[type="submit"]:hover {
    background-color: #00577a;
}

.error-message {
    color: red;
    font-weight: bold;
    margin-top: 10px;
}

</style>
</html>
