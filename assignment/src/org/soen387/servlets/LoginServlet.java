package org.soen387.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soen387.domain.User;
import org.soen387.services.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    protected UserService getUserService() {
		return UserService.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
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
            User user = getUserService().find(email, password);
            System.out.println("User: " + user);
            if (user != null) {
       			request.removeAttribute("messages");
            		HttpSession session = request.getSession();
            		session.setMaxInactiveInterval(20*60); // session expires in 20 minutes
        			session.setAttribute("email", email);
        			session.setAttribute("firstname", user.getFirstName());
        			response.sendRedirect(request.getContextPath() + "/search.jsp");
                return;
            } else {
                messages.put("login", "Unable to log on. Please make sure that the username and password are for a valid user.");
                System.out.println("Error");
            }  
        }
        
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
