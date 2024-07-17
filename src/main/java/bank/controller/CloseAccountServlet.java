package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.CustomerDAO;

@WebServlet("/closeAccount")
public class CloseAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");

        CustomerDAO customerDAO = new CustomerDAO();
        boolean success = customerDAO.closeAccount(accountNumber);

        if (success) {
            request.setAttribute("accountNumber", accountNumber);
            request.getRequestDispatcher("closeaccountsuccess.jsp").forward(request, response);
        } else {
            response.sendRedirect("closeaccountfailure.jsp");
        }
    }
}
