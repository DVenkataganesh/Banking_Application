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

@WebServlet("/register")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cus_id = request.getParameter("cus_id");
        String full_name = request.getParameter("full_name");
        String address = request.getParameter("address");
        String mobile_no = request.getParameter("mobile_no");
        String email_id = request.getParameter("email_id");
        String account_type = request.getParameter("account_type");
        int initial_balance = Integer.parseInt(request.getParameter("initial_balance"));
        String dob = request.getParameter("dob");
        String id_proof = request.getParameter("id_proof");
        String account_number = request.getParameter("Account_Number");
        String password = request.getParameter("Password");
        String account_status = request.getParameter("account-status");

        try {
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application", "root", "1682");

            String query = "INSERT INTO customer (customer_id, full_name, address, mobile_no, email_id, account_type, initial_balance, dob, id_proof, account_no, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, cus_id);
            pstmt.setString(2, full_name);
            pstmt.setString(3, address);
            pstmt.setString(4, mobile_no);
            pstmt.setString(5, email_id);
            pstmt.setString(6, account_type);
            pstmt.setInt(7, initial_balance);
            pstmt.setString(8, dob);
            pstmt.setString(9, id_proof);
            pstmt.setString(10, account_number);
            pstmt.setString(11, password);
            pstmt.setString(12, account_status);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("registration_success.jsp");
            } else {
                response.sendRedirect("registration_failure.jsp");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}