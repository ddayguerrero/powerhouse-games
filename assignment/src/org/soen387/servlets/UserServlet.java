package org.soen387.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soen387.business.ShoppingCart;
import org.soen387.datasource.mappers.UserMapper;
import org.soen387.payloads.UserPayload;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    
    protected UserMapper getUserService() {
    		return UserMapper.getInstance();
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
		String confirmPassword = request.getParameter("confirmPassword");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postal_code = request.getParameter("postal_code");
		String country = request.getParameter("country");
		
		if((fname.equals(null) || fname.isEmpty()) || (lname.equals(null) || lname.isEmpty()) || (email.equals(null) || email.isEmpty()) || (password.equals(null) || password.isEmpty())) {
			response.sendRedirect("register.jsp");
			System.out.println("EMPTY");
			return;
		}
		else {
			if(!password.equals(confirmPassword)) {
				request.setAttribute("error", "Passwords don't match!");
			    request.getRequestDispatcher("register.jsp").forward(request, response);
			    return;
			} else {
				UserPayload newUser = new UserPayload(fname, lname, email, password, address1, address2, city, province, postal_code, country);
				System.out.println(newUser);
				int result = getUserService().register(newUser);
				
				if (result == -1) {
					request.setAttribute("error", "An error occured while registering, please try again!");
				    request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				} else {
					// POST/REDIRECT/GET
					response.setStatus(HttpServletResponse.SC_SEE_OTHER);
					System.out.println("GOOD REGISTER");
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(60*60*24); // session expires in 20 minutes
					session.setAttribute("email", email);
					session.setAttribute("firstname", fname);
					
					ShoppingCart cart = new ShoppingCart();
					cart.setHasMemberPricing(true);
	        			if(session.getAttribute("cart") == null) {
	        				session.setAttribute("cart", cart);
	        			}
	        			
					request.getRequestDispatcher("search.jsp").forward(request, response);
				}
			}
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Modifying User...");
		
		JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());
		int id = obj.get("id").getAsInt();
		String fname = obj.get("fname").getAsString();
		String lname = obj.get("lname").getAsString();
		String email = obj.get("email").getAsString();
		String address1 = obj.get("address1").getAsString();
		String address2 = obj.get("address2").getAsString();
		String city = obj.get("city").getAsString();
		String province = obj.get("province").getAsString();
		String postal_code = obj.get("postalCode").getAsString();
		String country = obj.get("country").getAsString();
		
		if((fname.equals(null) || fname.isEmpty()) || (lname.equals(null) || lname.isEmpty()) || (email.equals(null) || email.isEmpty())) {
			request.getRequestDispatcher("/profile").forward(request, response);
			System.out.println("Error while modifying user...");
			return;
		} else {
			System.out.println("Userid: " + id);
			UserPayload updatedUserInfo = new UserPayload(fname, lname, email, address1, address2, city, province, postal_code, country);
			UserMapper.getInstance().updateProfile(id, updatedUserInfo);
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

}
