package org.soen387.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
		String id = request.getParameter("id");
		System.out.println("title " + title);
		System.out.println("id " + id);
		int gameId = id == null ? 0 : Integer.parseInt(id);
		
		if(title != null) {
			System.out.println("Process search");
			processSearch(request, response, title);
		}
		else if (gameId != 0){
			System.out.println("Get detailed game information");
			getDetailedGameInformation(request, response, gameId);
		}
	}
	
	/**
	 * Get detailed game information
	 * @param request - HttpServletRequest
	 * @param response - HttpServletRequest
	 * @param gameId - Game id
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getDetailedGameInformation(HttpServletRequest request, HttpServletResponse response, int gameId) throws ServletException, IOException {
		Game game = GameService.getInstance().getGameById(gameId);
		if (game != null) {
			request.setAttribute("game", game);
			request.getServletContext().getRequestDispatcher("/game.jsp").forward(request, response);
		} else {
			System.out.println("Game not found");
			return;
		}
	}

	/**
	 * Search game by title
	 * @param request - HttpServletRequest
	 * @param response - HttpServletRequest
	 * @param title - Game title
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processSearch(HttpServletRequest request, HttpServletResponse response, String title) throws ServletException, IOException {
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
