package servlets;

import bank.model.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewCustomersServlet")
public class ViewCustomersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jdbcURL = "jdbc:mysql://localhost:3306/banking_application";
        String dbUser = "root";
        String dbPassword = "1682";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Customer> customers = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT * FROM customer";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setFullName(resultSet.getString("full_name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setMobileNumber(resultSet.getString("mobile_no"));
                customer.setEmail(resultSet.getString("email_id"));
                customer.setTypeOfAccount(resultSet.getString("account_type"));
                customer.setInitialBalance(resultSet.getDouble("initial_balance"));
                customer.setDateOfBirth(resultSet.getString("dob"));
                customer.setIdProof(resultSet.getString("id_proof"));
                customer.setAccountNumber(resultSet.getString("account_no"));
                customer.setPassword(resultSet.getString("password"));
                customer.setStatus(resultSet.getString("status"));
                customers.add(customer);
            }

            request.setAttribute("customers", customers);
            request.getRequestDispatcher("CustomerDetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
