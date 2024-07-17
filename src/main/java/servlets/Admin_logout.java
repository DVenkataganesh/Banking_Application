package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Admin_logout
 */
@WebServlet("/Admin_logout")
public class Admin_logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Log logout event (optional)
            String adminUsername = (String) session.getAttribute("admin");
            if (adminUsername != null) {
                System.out.println("Admin " + adminUsername + " logged out.");
            }
            session.invalidate();
        }
        
        // Optionally set a logout message
        request.setAttribute("logoutMessage", "You have successfully logged out.");
        
        // Redirect to login page with logout message
        response.sendRedirect("admin_login.jsp?logout=true");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
