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

/**
 * Servlet implementation class SpecialsServlet
 */
@WebServlet("/specials")
public class SpecialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecialsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Game> games = GameMapper.getInstance().getDiscountedGames();
		int numOfRows = games.size() / 3;
		int index = 0;
		int remainder = games.size() % 3;
		System.out.println("GAMESS");
		
		if (games != null) {
			request.setAttribute("games", games);
			request.setAttribute("numOfRows", numOfRows-1);
			request.setAttribute("remainder", remainder);
			request.getServletContext().getRequestDispatcher("/specials.jsp").forward(request, response);
		}
	}

}
