package org.soen387.services;

import java.util.ArrayList;

import org.soen387.beans.AdvancedSearchBean;
import org.soen387.beans.GamePayload;
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
	 * Initiates call to retrieve a list of all game inventory
	 */
	public ArrayList<Game> getAllGames() {
		System.out.println("Getting all games...");
		return GameTDG.getInstance().getAllGames();
	}
	
	/**
	 * Initiates call to retrieve a list of games by title
	 * @param title - Game title
	 * @return list of Games
	 */
	public ArrayList<Game> getGamesByTitle(String title) {
		System.out.println("Getting games by title...");
		return GameTDG.getInstance().getGamesByTitle(title);
	}
	
	/**
	 * Initiates call to retrieve a list of games by advanced search query
	 * @param advSearchBean - Search query
	 * @return list of Games
	 */
	public ArrayList<Game> getGames(AdvancedSearchBean advSearchBean) {
		System.out.println("Getting games via advanced search...");
		return GameTDG.getInstance().getGamesByAdvanced(advSearchBean);
	}
	
	/**
	 * Initiates call to retrieve a list of discounted games
	 * @param advSearchBean
	 * @return list of discounted Games
	 */
	public ArrayList<Game> getDiscountedGames() {
		System.out.println("Getting discounted games...");
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
	 * Initiates call to insert new game
	 * @return
	 */
	public int insertNewGame(GamePayload gamePayload) {
		return GameTDG.getInstance().insertGame(gamePayload);
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
