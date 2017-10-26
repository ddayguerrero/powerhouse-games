package org.soen387.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.domain.Game;
import org.soen387.services.GameService;
import org.soen387.services.UserService;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/game")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
    }
    
//    protected GameService getUserService() {
//		return UserService.getInstance();
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title =  request.getParameter("title");
		ArrayList<Game> games = GameService.getInstance().getGameByTitle(title);
		if (games != null) {
			request.setAttribute("games", games);
			request.getServletContext().getRequestDispatcher("/search_results.jsp").forward(request, response);
		} else {
			System.out.println("Game not found");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
