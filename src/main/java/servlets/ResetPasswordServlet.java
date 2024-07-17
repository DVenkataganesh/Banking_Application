package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("account_no"); // Get account number from form
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");

        if (newPassword != null && !newPassword.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {
            if (newPassword.equals(confirmPassword)) {
                try {
                    // Update password and increment login count in the database
                    boolean isUpdated = updatePassword(accountNumber, newPassword);

                    if (isUpdated) {
                        request.setAttribute("message", "Password successfully reset.");
                    } else {
                        request.setAttribute("message", "Failed to reset password.");
                    }
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Customer_login.jsp");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Error resetting password: " + e.getMessage());
                    RequestDispatcher dispatcher = request.getRequestDispatcher("resetpassword.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("message", "Passwords do not match.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("resetpassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("message", "Please enter both passwords.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("resetpassword.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean updatePassword(String accountNumber, String newPassword) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682")) {
            connection.setAutoCommit(false); // Start a transaction

            String sql = "UPDATE customer SET password = ?, logins = IFNULL(logins, 0) + 1 WHERE account_no = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newPassword);
                statement.setString(2, accountNumber);
                
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    connection.commit(); // Commit the transaction
                    return true;
                } else {
                    connection.rollback(); // Rollback if no rows affected
                    return false;
                }
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
            throw e;
        }
    }
}
