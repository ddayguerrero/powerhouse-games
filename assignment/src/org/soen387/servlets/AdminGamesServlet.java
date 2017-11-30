package org.soen387.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.domain.Game;
import org.soen387.payloads.GamePayload;
import org.soen387.services.GameService;
import org.soen387.services.UserService;

/**
 * Servlet implementation class AdminGamesServlet
 */
@WebServlet(urlPatterns = {"/admin/games", "/admin/games/edit"})
public class AdminGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String editUrlPath = "/admin/games/edit";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGamesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected GameService getGameService() {
		return GameService.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Game> games = GameService.getInstance().getAllGames();
		if (games != null) {
			request.setAttribute("games", games);
			request.getServletContext().getRequestDispatcher("/admin/games.jsp").forward(request, response);
		} else {
			System.out.println("Games not found");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String defaultCoverArtURL = "https://static1.squarespace.com/static/52f29ad2e4b02d1f9d476561/5390b6b6e4b052006822dd29/5390b747e4b06374d7eb79fb/1401993081305/noCoverArt.gif?format=500w";
		String urlPath = request.getServletPath();
		System.out.println("Add or Edit: " + urlPath);
		if(urlPath.equals(this.editUrlPath)) {
			int id =  Integer.parseInt(request.getParameter("edit_id"));
			String title =  request.getParameter("edit_title");
			String console = request.getParameter("edit_console");
			String players = request.getParameter("edit_players");
			String description = request.getParameter("edit_description");
			Boolean coop = request.getParameter("edit_coop").equals("No") ? false : true;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsed = null;
			try {
				parsed = format.parse(request.getParameter("edit_release"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date release = new java.sql.Date(parsed.getTime());
			String developer = request.getParameter("edit_developer");
			String publisher = request.getParameter("edit_publisher");
			String front_cover = request.getParameter("edit_front");
			String back_cover = request.getParameter("edit_back");
			if(front_cover == null || front_cover.isEmpty()) {
				front_cover = defaultCoverArtURL;
			}
			if(back_cover == null || back_cover.isEmpty()) {
				back_cover = defaultCoverArtURL;
			}
			String genre = request.getParameter("edit_genre");
			BigDecimal price = new BigDecimal(request.getParameter("edit_price"));
			BigDecimal discount = new BigDecimal(request.getParameter("edit_discount"));
			GamePayload gp = new GamePayload(id, title, description, console, players, coop, genre, release, developer, publisher, front_cover, back_cover, price, discount);
			
			GameService.getInstance().modifyGame(id, gp);
			// POST/REDIRECT/GET
			response.setStatus(HttpServletResponse.SC_SEE_OTHER);
			doGet(request, response);
		} else {
			String title =  request.getParameter("title");
			String console = request.getParameter("console");
			String players = request.getParameter("players");
			String description = request.getParameter("description");
			Boolean coop = request.getParameter("coop").equals("No") ? false : true;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsed = null;
			try {
				parsed = format.parse(request.getParameter("release"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date release = new java.sql.Date(parsed.getTime());
			String developer = request.getParameter("developer");
			String publisher = request.getParameter("publisher");
			String front_cover = request.getParameter("front");
			String back_cover = request.getParameter("back");
			if(front_cover == null || front_cover.isEmpty()) {
				front_cover = defaultCoverArtURL;
			}
			if(back_cover == null || back_cover.isEmpty()) {
				back_cover = defaultCoverArtURL;
			}
			String genre = request.getParameter("genre");
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			BigDecimal discount = new BigDecimal(request.getParameter("discount"));
			
			GamePayload gp = new GamePayload(-1, title, description, console, players, coop, genre, release, developer, publisher, front_cover, back_cover, price, discount);
			int result = getGameService().insertNewGame(gp);
			
			if (result == -1) {
				request.setAttribute("error", "An error occured while adding new game, please try again!");
			    request.getRequestDispatcher("/admin/games/").forward(request, response);
				return;
			} else {
				// POST/REDIRECT/GET
				response.setStatus(HttpServletResponse.SC_SEE_OTHER);
				doGet(request, response);
			}
		}
	}

}
