package org.soen387.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soen387.beans.UserBean;
import org.soen387.services.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(urlPatterns = {"/user", "/register"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		request.getServletContext().getRequestDispatcher("/register.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postal_code = request.getParameter("postal_code");
		String country = request.getParameter("country");
		
		UserBean newUser = new UserBean(fname, lname, email, password, address1, address2, city, province, postal_code, country);
		System.out.println(newUser);
		int result = getUserService().register(newUser);
		
		if (result == -1) {
			response.sendRedirect("register.jsp");
			return;
		} else {
			// POST/REDIRECT/GET
			response.setStatus(HttpServletResponse.SC_SEE_OTHER);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(20*60); // session expires in 20 minutes
			session.setAttribute("email", email);
			session.setAttribute("firstname", fname);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}
	}

}
