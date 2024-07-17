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

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        double amount = Double.parseDouble(request.getParameter("amount"));

        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomer(accountNumber);

        if (customer != null) {
            boolean success = customerDAO.deposit(accountNumber, amount);

            if (success) {
                // Update the balance in the customer object
                customer.setInitialBalance(customer.getInitialBalance() + amount);
                
                customerDAO.logTransaction(accountNumber, "Deposit", amount, customer.getInitialBalance());

             // Update the session with the new balance
             HttpSession session = request.getSession();
             session.setAttribute("customer", customer);


                request.setAttribute("newBalance", customer.getInitialBalance());
                request.getRequestDispatcher("DepositSuccess.jsp").forward(request, response);
            } else {
                response.sendRedirect("DepositFailure.jsp");
            }
        } else {
            response.sendRedirect("AccountNotFound.jsp");
        }
    }
}
