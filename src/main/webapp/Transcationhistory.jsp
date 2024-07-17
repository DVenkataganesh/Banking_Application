<%@ page import="java.util.List" %>
<%@ page import="bank.model.Transaction" %>

<!DOCTYPE html>
<html>
<head>
    <title>Transaction History</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to your CSS file -->
</head>
<body>
    <div class="container">
        <h1>Transaction History</h1>
        <table border="1">
            <tr>
                <th>Transaction Type</th>
                <th>Amount</th>
                <th>Transaction Date</th>
                <th>Balance After Transaction</th>
            </tr>
            <%
                List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
                if (transactions != null && !transactions.isEmpty()) {
                    for (Transaction transaction : transactions) {
            %>
            <tr>
                <td><%= transaction.getTransactionType() %></td>
                <td><%= transaction.getAmount() %></td>
                <td><%= transaction.getTransactionDate() %></td>
                <td><%= transaction.getBalanceAfterTransaction() %></td>
            </tr>
            <%
                    }
                } else {
                    out.println("<tr><td colspan='4'>No transactions found.</td></tr>");
                }
            %>
        </table>
        <a href="customer_dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
