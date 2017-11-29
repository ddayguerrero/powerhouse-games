package org.soen387.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.soen387.domain.Game;
import org.soen387.domain.User;
import org.soen387.services.UserService;

/**
 * Servlet implementation class Favorites
 */
@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoritesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = UserService.getInstance().findByEmail((String)request.getSession().getAttribute("email"));
		response.setContentType("text/html");
		List<Game> favorites = UserService.getInstance().getFavorites(user.getUserid());
        request.setAttribute("favorites", favorites);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = UserService.getInstance().findByEmail((String)request.getSession().getAttribute("email"));
        String gameId = request.getParameter("gameId");
        String remove = request.getParameter("remove");
		System.out.println("Adding / Removing to Favorites: " + gameId + " for user " + user.getUserid());
        if(remove.equals("true")) {
        		UserService.getInstance().removeFavorite(user.getUserid(), gameId);
        }
        else if(remove.equals("false")) {
        		UserService.getInstance().addFavorite(user.getUserid(), gameId);
        }
        response.setContentType("text/html");
        List<Game> favorites = UserService.getInstance().getFavorites(user.getUserid());
        request.setAttribute("favorites", favorites);
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}

}
