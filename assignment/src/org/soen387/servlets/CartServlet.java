package org.soen387.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soen387.business.ShoppingCart;
import org.soen387.domain.CartItem;
import org.soen387.domain.Game;
import org.soen387.services.GameService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(urlPatterns = { "/cart", "/cart/item" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final String itemPath = "/cart/item";
	 private final String cartPath = "/cart";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		ShoppingCart cart = new ShoppingCart();
		if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", cart);
		} else {
			cart = (ShoppingCart) session.getAttribute("cart");
		}
		request.setAttribute("cart", cart);
		request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	 
		ShoppingCart cart = new ShoppingCart();
		if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", cart);
		} else {
			cart = (ShoppingCart) session.getAttribute("cart");
		}
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		CartItem<Game> item = new CartItem<Game>();
		Game game = GameService.getInstance().getGameById(gameId);
		item.setItem(game);
		cart.addToCart(item);
		response.setStatus(HttpServletResponse.SC_SEE_OTHER);
		//request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
		response.sendRedirect("cart.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getServletPath();
		if (searchType.equals(this.itemPath)) {
			Gson gson = new Gson();
	        JsonParser parser = new JsonParser();
	        JsonObject obj = (JsonObject) parser.parse(request.getReader());
	        int gameId = obj.get("itemId").getAsInt();
	        int action = obj.get("action").getAsInt();
	        
	        ShoppingCart cart = new ShoppingCart();
	        HttpSession session = request.getSession();
	        if(session.getAttribute("cart") == null) {
				session.setAttribute("cart", cart);
			} else {
				cart = (ShoppingCart) session.getAttribute("cart");
			}
	        
	        cart.updateQuantity(gameId, action);
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getServletPath();
		System.out.println(searchType);
		
		ShoppingCart cart = new ShoppingCart();
        HttpSession session = request.getSession();
        if(session.getAttribute("cart") == null) {
			session.setAttribute("cart", cart);
		} else {
			cart = (ShoppingCart) session.getAttribute("cart");
		}
		
		if (searchType.equals(this.itemPath)) {
	        JsonParser parser = new JsonParser();
	        JsonObject obj = (JsonObject) parser.parse(request.getReader());
	        int gameId = obj.get("itemId").getAsInt();
	        System.out.println("item id to delete: " + gameId);

	        cart.removeFromCart(gameId);
	        response.setStatus(HttpServletResponse.SC_OK);
		} else if(searchType.equals(this.cartPath)) {
			System.out.println("Emptying cart...");
			cart.emptyCart();
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

}
