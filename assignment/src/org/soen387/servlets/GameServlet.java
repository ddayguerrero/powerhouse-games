package org.soen387.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.datasource.mappers.GameMapper;
import org.soen387.domain.Game;
import org.soen387.payloads.AdvancedSearchPayload;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet(urlPatterns = {"/game", "/game/advanced"})
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String advancedSearchPath = "/game/advanced";
    private final String simpleSearchPath = "/game";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getServletPath();
		String title =  request.getParameter("title");
		String id = request.getParameter("id");
		String console = request.getParameter("console");
		String year = request.getParameter("year");
		String publisher = request.getParameter("publisher");
		String genre = request.getParameter("genre");
		System.out.println(searchType);
		
		if(searchType.equals(this.simpleSearchPath)) {
			int gameId = id == null ? 0 : Integer.parseInt(id);
			
			if(title != null) {
				System.out.println("Process search");
				processSearch(request, response, title);
			}
			else if (gameId != 0){
				System.out.println("Get detailed game information");
				getDetailedGameInformation(request, response, gameId);
			}
		} else if(searchType.equals(this.advancedSearchPath)) {
			System.out.println("Process advanced search");
			AdvancedSearchPayload advSearchBean = new AdvancedSearchPayload(title, console, year, publisher, genre);
			processAdvancedSearch(request, response, advSearchBean);
		}

	}
	
	/**
	 * Search game by multiple fields
	 * @param advSearchBean - Search query
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processAdvancedSearch(HttpServletRequest request, HttpServletResponse response, AdvancedSearchPayload advSearchBean) throws ServletException, IOException {
		ArrayList<Game> games = GameMapper.getInstance().getGames(advSearchBean);
		if (games != null) {
			request.setAttribute("games", games);
			request.getServletContext().getRequestDispatcher("/search_results.jsp").forward(request, response);
		} else {
			System.out.println("Game not found");
			return;
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
		Game game = GameMapper.getInstance().getGameById(gameId);
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
		ArrayList<Game> games = GameMapper.getInstance().getGamesByTitle(title);
		if (games != null) {
			request.setAttribute("games", games);
			request.getServletContext().getRequestDispatcher("/search_results.jsp").forward(request, response);
		} else {
			System.out.println("Games not found");
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
