package org.soen387.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.domain.User;
import org.soen387.services.UserService;
import org.soen387.util.Generator;
import org.soen387.util.Mailer;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet(urlPatterns = {"/password", "/forgotpassword"})
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected UserService getUserService() {
		return UserService.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getServletContext().getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sending Email...");
		String email = request.getParameter("email");
		Map<String, String> messages = new HashMap<String, String>();
		
//		try {
		User userToReset = getUserService().findByEmail(email);
		if (userToReset == null) {
			System.out.println("No user");
			messages.put("pass", "Sorry, the e-mail does not exist.");
		    //request.getRequestDispatcher("/password.jsp").forward(request, response);
		} else {
			request.removeAttribute("messages");
			String recipient = userToReset.getEmail();
			String temporaryPassword = Generator.generateRandomString();
			Timestamp expiry = new Timestamp(System.currentTimeMillis() + 60*60*24*1000); // 24H
			System.out.println(expiry);
			getUserService().setTemporaryPassword(userToReset.getUserid(), temporaryPassword, expiry);
			getUserService().updateUser(userToReset.getUserid(), 0);
	        String subject = "Power House Games - Password Reset";
	        String content = "Your temporary Password (24H) is: " + temporaryPassword;
	        String resultMessage = "";
			try {
	            Mailer.sendEmail(recipient, subject, content);
	            resultMessage = "The e-mail was sent successfully to: " + recipient;
	            messages.put("pass", resultMessage);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            resultMessage = "There were an error: " + ex.getMessage();
	            messages.put("pass", resultMessage);
	        } finally {
	            request.setAttribute("messages", resultMessage);
	        }
//			}
		}
		request.setAttribute("messages", messages);
		System.out.println("/forgotpassword.jsp");
		request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
	}

}
