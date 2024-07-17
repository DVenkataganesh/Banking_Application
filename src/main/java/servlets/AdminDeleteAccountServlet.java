package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminDeleteAccount")
public class AdminDeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");

            // Step 1: Retrieve customer data and check balance
            PreparedStatement pstmtCheck = conn.prepareStatement("SELECT initial_balance FROM customer WHERE account_no = ?");
            pstmtCheck.setString(1, accountNo);
            ResultSet rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("initial_balance");

                // Set the balance in the request
                request.setAttribute("balance", balance);
                request.setAttribute("account_no", accountNo); // Pass account number for deletion confirmation

                // Forward to delete confirmation page
                RequestDispatcher dispatcher = request.getRequestDispatcher("delete_confirmation.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Account not found.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admindashboard.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("admindashboard.jsp");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
