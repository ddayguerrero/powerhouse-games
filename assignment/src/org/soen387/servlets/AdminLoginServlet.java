package org.soen387.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soen387.domain.Admin;
import org.soen387.services.AdminService;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
    }
    
    protected AdminService getAdminService() {
		return AdminService.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getServletContext().getRequestDispatcher("/admin/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Map<String, String> messages = new HashMap<String, String>();
		
		if (email == null || email.isEmpty()) {
            messages.put("username", "Please enter username");
        }

        if (password == null || password.isEmpty()) {
            messages.put("password", "Please enter password");
        }
        if (messages.isEmpty()) {
        		Admin admin = getAdminService().find(email, password);
        		if (admin != null) {
       			request.removeAttribute("messages");
            		HttpSession session = request.getSession();
            		//java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            		//String old_login = getUserService().updateLastLogin(user, date.toString());
            		
            		session.setMaxInactiveInterval(20*60); // session expires in 20 minutes of idle
        			session.setAttribute("email", email);
//        			session.setAttribute("firstname", user.getFirstName());
//        			session.setAttribute("last_login", old_login);
        			response.sendRedirect(request.getContextPath() + "/admin/users");
        			System.out.println("Login Admin!");
                return;
            } else {
                messages.put("login", "Unable to log in. Please make sure that your credentials are correct.");
            }  
        }
        
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
	}

}
