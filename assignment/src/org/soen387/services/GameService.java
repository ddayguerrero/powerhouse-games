package org.soen387.services;

import java.util.ArrayList;

import org.soen387.beans.AdvancedSearchBean;
import org.soen387.datasource.gateways.GameTDG;
import org.soen387.domain.Game;

/**
 * Service used to access defined Game models
 * @author Darrel Guerrero - 27352409
 *
 */
public class GameService {
	
	public static GameService instance = null;
	
	private GameService() { }
	
	/**
	 * Initiates call to retrieve a list of games by title
	 * @param title - Game title
	 * @return list of Games
	 */
	public ArrayList<Game> getGamesByTitle(String title) {
		return GameTDG.getInstance().getGamesByTitle(title);
	}
	
	/**
	 * Initiates call to retrieve a list of games by advanced search query
	 * @param advSearchBean - Search query
	 * @return list of Games
	 */
	public ArrayList<Game> getGames(AdvancedSearchBean advSearchBean) {
		return GameTDG.getInstance().getGamesByAdvanced(advSearchBean);
	}
	
	/**
	 * Initiates call to retrieve a list of discounted games
	 * @param advSearchBean
	 * @return list of discounted Games
	 */
	public ArrayList<Game> getDiscountedGames() {
		System.out.println("Getting games...");
		return GameTDG.getInstance().getDiscountedGames();
	}
	
	/**
	 * Initiates call to retrieve a single game by its id
	 * @param id - Game id
	 * @return Game
	 */
	public Game getGameById(int id) {
		return GameTDG.getInstance().getGameById(id);
	}
	
	/**
	 * Singleton instance of user service
	 * @return GameService
	 */
	public static GameService getInstance() {
		if (instance == null) {
			instance = new GameService();
		}
		return instance;
	}
}
