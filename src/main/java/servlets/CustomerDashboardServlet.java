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

@WebServlet("/customer/dashboard")
public class CustomerDashboardServlet extends HttpServlet {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String accountNo = request.getParameter("account_no");
	    HttpSession session = request.getSession();


	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");) {
	        String sql = "SELECT * FROM customer WHERE account_no = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, accountNo);

	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            // Extract data from the ResultSet
	            String accountNumber = resultSet.getString("account_no");
	            int balance = resultSet.getInt("initial_balance"); // or resultSet.getBigDecimal("initial_balance")
	            String fullName = resultSet.getString("full_name");
                String emailId = resultSet.getString("email_id");
                String accountType = resultSet.getString("account_type");

	            // Set the data as request attributes
	            request.setAttribute("accountNumber", accountNumber);
	            request.setAttribute("balance", balance);
	            request.setAttribute("fullName", fullName);
	            request.setAttribute("emailId", emailId);
	            request.setAttribute("accountType", accountType);
	            session.setAttribute("balance", balance);

	            // Forward to the JSP page
	            request.getRequestDispatcher("Customer_dashboard.jsp").forward(request, response);
	        } else {
	            response.sendRedirect("Customer_dashboard.jsp?error=Account not found");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        response.sendRedirect("Customer_dashboard.jsp?error=Database error");
	    }
	}
} 