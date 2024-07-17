package bank.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.CustomerDAO;
import bank.model.Transaction;

@WebServlet("/transactionHistory")
public class TransactionHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String accountNumber = (String) session.getAttribute("accountNo");

            if (accountNumber != null) {
                CustomerDAO customerDAO = new CustomerDAO();
                List<Transaction> transactions = customerDAO.getTransactionHistory1(accountNumber);

                request.setAttribute("transactions", transactions);
                request.getRequestDispatcher("Transcationhistory.jsp").forward(request, response);
            } else {
                response.sendRedirect("Accountnotfound.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
