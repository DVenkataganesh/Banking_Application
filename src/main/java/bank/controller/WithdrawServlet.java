package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.CustomerDAO;
import bank.model.Customer;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        double amount = Double.parseDouble(request.getParameter("amount"));

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomer(accountNumber);

        if (customer != null) {
            try {
                boolean success = customerDAO.withdraw(accountNumber, amount);
                if (success) {
                    // Update the customer's balance
                    customer.setInitialBalance(customer.getInitialBalance() - amount);
                    
                    // Log the transaction
                    customerDAO.logTransaction(accountNumber, "Withdraw", amount, customer.getInitialBalance());

                 // Update the session with the new customer data
                 HttpSession session = request.getSession();
                 session.setAttribute("customer", customer);


                    request.setAttribute("accountNumber", accountNumber);
                    request.setAttribute("amount", amount);
                    request.setAttribute("newBalance", customer.getInitialBalance());
                    request.setAttribute("fullName", customer.getFullName());
                    request.setAttribute("address", customer.getAddress());
                    request.setAttribute("mobileNumber", customer.getMobileNumber());
                    request.setAttribute("email", customer.getEmail());
                    request.setAttribute("typeOfAccount", customer.getTypeOfAccount());
                    request.getRequestDispatcher("withdrawsuccess.jsp").forward(request, response);
                } else {
                    response.sendRedirect("withdrawfailure.jsp");
                }
            } catch (Exception e) {
                // Log the error and redirect to an error page
                e.printStackTrace(); // Optional: Log the exception
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("AccountNotFound.jsp");
        }
    }
}
