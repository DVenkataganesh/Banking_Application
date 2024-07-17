package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        String fullName = request.getParameter("full_name");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobile_no");
        String emailId = request.getParameter("email_id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");
            PreparedStatement pst = con.prepareStatement("UPDATE customer SET full_name =?, address =?, mobile_no =?, email_id =? WHERE account_no =?");
            pst.setString(1, fullName);
            pst.setString(2, address);
            pst.setString(3, mobileNo);
            pst.setString(4, emailId);
            pst.setString(5, accountNo);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                // Successful update
                request.setAttribute("message", "Customer details updated successfully.");
            } else {
                // Update failed
                request.setAttribute("message", "Error updating customer details.");
            }
            request.getRequestDispatcher("modify_customer.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error (e.g., redirect to an error page)
        }
    }
}