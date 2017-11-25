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

import org.soen387.business.ShoppingCart;
import org.soen387.domain.User;
import org.soen387.services.CheckoutService;
import org.soen387.services.UserService;
import org.soen387.util.LuhnValidator;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		ShoppingCart cart = new ShoppingCart();
		if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", cart);
		} else {
			cart = (ShoppingCart) session.getAttribute("cart");
		}
		if (cart.getCurrentQuantity() == 0) {
			response.setContentType("text/html");
			request.getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
		} else {
			response.setContentType("text/html");
			request.getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cardholdername = request.getParameter("cardholdername");
		String cardnumber = request.getParameter("cardnumber");
		int expirymonth = Integer.parseInt(request.getParameter("expirymonth"));
		int expiryYear = Integer.parseInt(request.getParameter("expiryyear"));
		
		Map<String, String> messages = new HashMap<String, String>();
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);
		
		boolean isCreditCardValid = LuhnValidator.isValid(cardnumber);
		if(isCreditCardValid && (year < expiryYear || (year == expiryYear && month <= expirymonth))) {
			System.out.println("Valid");
			HttpSession session = request.getSession();
			ShoppingCart cart = new ShoppingCart();
			if(session.getAttribute("cart") == null) {
				session.setAttribute("cart", cart);
			} else {
				cart = (ShoppingCart) session.getAttribute("cart");
			}
			User user = UserService.getInstance().findByEmail((String)session.getAttribute("email"));
			int result = CheckoutService.getInstance().createInvoice(user, cart);
			//Post / Redirect / Get
			response.setStatus(HttpServletResponse.SC_SEE_OTHER);
		} else {
			messages.put("error", "Expired Card or Invalid Payment Info");
			request.setAttribute("messages", messages);
			System.out.println("Expired Card or Invalid Payment Info");
	        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
		}
	}

}
