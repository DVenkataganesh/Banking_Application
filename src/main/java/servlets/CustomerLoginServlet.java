package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.model.Customer;

@WebServlet("/customerloginn")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");

            pstmt = conn.prepareStatement("SELECT * FROM customer WHERE account_no = ? AND password = ?");
            pstmt.setString(1, accountNo);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                String accountStatus = rs.getString("status"); // Assuming there's a status column

                if ("Closed".equalsIgnoreCase(accountStatus)) {
                    // Redirect to account closed page
                    request.setAttribute("error", "Your account is closed. Please contact support.");
                    request.getRequestDispatcher("Customer_login.jsp").forward(request, response);
                    return;
                }

                Customer customer = new Customer();
                customer.setAccountNumber(rs.getString("account_no"));
                customer.setInitialBalance(rs.getDouble("initial_balance"));
                customer.setFullName(rs.getString("full_name"));
                customer.setEmail(rs.getString("email_id"));
                customer.setTypeOfAccount(rs.getString("account_type"));

                // Set other customer attributes as needed
                session.setAttribute("customer", customer);
                session.setAttribute("accountNo", accountNo); // Ensure accountNo is set in the session

                int logins = rs.getInt("logins");

                if (logins > 0) {
                    // Redirect to customer dashboard
                    response.sendRedirect("customer_dashboard.jsp");
                } else {
                    // Redirect to password reset page
                    response.sendRedirect("resetpassword.jsp");
                }
            } else {
                // Invalid credentials, display error message
                request.setAttribute("error", "Invalid account number or password.");
                request.getRequestDispatcher("Customer_login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database driver not found.");
            request.getRequestDispatcher("Customer_login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("Customer_login.jsp").forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
