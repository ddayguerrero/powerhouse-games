package org.soen387.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.datasource.mappers.GameMapper;
import org.soen387.domain.Game;
import org.soen387.util.Mailer;

/**
 * Servlet implementation class AdminSpecialsServlet
 */
@WebServlet(urlPatterns = {"/admin/specials"})
public class AdminSpecialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSpecialsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Game> games = GameMapper.getInstance().getDiscountedGames();
		if (games != null) {
			request.setAttribute("games", games);
			request.getServletContext().getRequestDispatcher("/admin/game_specials.jsp").forward(request, response);
		} else {
			System.out.println("Games not found");
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Game> games = GameMapper.getInstance().getDiscountedGames();
		System.out.println("Sending newsletter...");
		// Send Newsletter Email
		Timestamp dateOfSale = new Timestamp(System.currentTimeMillis());
        String subject = "This week's PGH specials!";	        
        String recipient = "ddayy.guerrero@hotmail.com"; // TODO
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        writer.println("Don't miss out of the POWERFUL deals!");
        writer.println("");
        writer.println("Order Details");
        writer.println("Week of: " + dateOfSale);
        writer.println("");
        writer.println("");
        	for (Game game: games) {
    			writer.println(game.getGameTitle() + "          " + game.getConsole() + "           " + game.getDiscount());
        }
        writer.println("");
        writer.println("");
        String content = stringWriter.toString();
        
        try {
            Mailer.sendEmail(recipient, subject, content);
        } catch (Exception ex) {
            System.out.println("Error in newsletter sending email: " + ex.getMessage());
        }
        
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        doGet(request, response);
	}
}
