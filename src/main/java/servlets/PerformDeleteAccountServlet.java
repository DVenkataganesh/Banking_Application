package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/performDeleteAccount")
public class PerformDeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");

            // Delete the customer account
            PreparedStatement pstmtDeleteCustomer = conn.prepareStatement("DELETE FROM customer WHERE account_no = ?");
            pstmtDeleteCustomer.setString(1, accountNo);
            int rowsAffected = pstmtDeleteCustomer.executeUpdate();

            if (rowsAffected > 0) {
                request.setAttribute("message", "Account successfully deleted.");
            } else {
                request.setAttribute("message", "Failed to delete the account.");
            }
            request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
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
