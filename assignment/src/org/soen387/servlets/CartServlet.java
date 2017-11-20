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

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String searchType = request.getScheme();
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
		request.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
	}

}
