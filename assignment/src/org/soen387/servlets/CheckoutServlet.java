package org.soen387.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
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
import org.soen387.datasource.gateways.InvoiceDetailsTDG;
import org.soen387.datasource.mappers.InvoiceMapper;
import org.soen387.datasource.mappers.UserMapper;
import org.soen387.domain.CartItem;
import org.soen387.domain.Game;
import org.soen387.domain.User;
import org.soen387.util.Generator;
import org.soen387.util.LuhnValidator;
import org.soen387.util.Mailer;

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
			// Create Invoice
			User user = UserMapper.getInstance().findByEmail((String)session.getAttribute("email"));
			int result = InvoiceMapper.getInstance().createInvoice(user, cart);
			
			// Send Invoice Email
			String recipient = user.getEmail();
			Timestamp dateOfSale = new Timestamp(System.currentTimeMillis());
	        String subject = "Thank you for ordering from Power House Games!";	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter, true);
	        writer.println("Hi " + user.getFirstName() + ",");
	        writer.println("");
	        writer.println("Your order was successfully received and we're processing it for you right now!");
	        writer.println("");
	        writer.println("Order Details");
	        writer.println("Time: " + dateOfSale);
	        writer.println("");
	        writer.println("Title ========== Console ========== Price ========== Discount ========== Quantity ========== Total");
	        writer.println("");
	        if(session != null && session.getAttribute("email") != null) {
		        	for (CartItem<?> item: cart.getItems()) {
	        			Game game = (Game)item.getItem();
	        			writer.println(game.getGameTitle() + "          " + game.getConsole() + "           " + game.getPrice() + "           " + game.getDiscount() + "           " + item.getQuantity() + "           " + item.getMemberPricingTotal());
		        }
			} else {
		        for (CartItem<?> item: cart.getItems()) {
	        			Game game = (Game)item.getItem();
	        			writer.println(game.getGameTitle() + "          " + game.getConsole() + "           " + game.getPrice() + "           " + game.getDiscount() + "           " + item.getQuantity() + "           " + item.getTotal());
		        }
			}
	        writer.println("");
	        writer.println("");
	        writer.println("Sub-total: " + cart.getSubTotal());
	        writer.println("Taxes: " + cart.getCalculatedTaxes());
	        writer.println("Grand Total: " +cart.getGrandTotal());
	        String content = stringWriter.toString();
	        
	        try {
	            Mailer.sendEmail(recipient, subject, content);
	        } catch (Exception ex) {
	            System.out.println("Error in sending email: " + ex.getMessage());
	        }
	        
	        cart.emptyCart();
	        session.setAttribute("cart", cart);
			//Post / Redirect / Get
			response.setStatus(HttpServletResponse.SC_SEE_OTHER);
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} else {
			messages.put("error", "Expired Card or Invalid Payment Info");
			request.setAttribute("messages", messages);
			System.out.println("Expired Card or Invalid Payment Info");
	        request.getRequestDispatcher("/checkout.jsp").forward(request, response);
		}
	}

}
