package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.model.Customer;

@WebServlet("/fetchCustomer")
public class FetchCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        Customer customer = null; // Assuming you have a Customer class to hold the details

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM customer WHERE account_no = ?");
            pst.setString(1, accountNo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setAccountNumber(rs.getString("account_no"));
                customer.setFullName(rs.getString("full_name"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNumber(rs.getString("mobile_no"));
                customer.setEmail(rs.getString("email_id"));
                // Set other fields as necessary
            }

            request.setAttribute("customer", customer);
            request.getRequestDispatcher("modify_customer.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error (e.g., redirect to an error page)
        }
    }
}
