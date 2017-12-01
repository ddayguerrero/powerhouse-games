package org.soen387.datasource.mappers;

import java.util.ArrayList;

import org.soen387.datasource.gateways.GameTDG;
import org.soen387.domain.Game;
import org.soen387.payloads.AdvancedSearchPayload;
import org.soen387.payloads.GamePayload;

/**
 * Service used to access defined Game models
 * @author Darrel Guerrero - 27352409
 *
 */
public class GameMapper {
	
	public static GameMapper instance = null;
	
	private GameMapper() { }
	
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
	public ArrayList<Game> getGames(AdvancedSearchPayload advSearchBean) {
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
	 * Initiates call to modify a game
	 * @param id - Game id
	 * @param gp - Game information
	 */
	public void modifyGame(int id, GamePayload gp) {
		GameTDG.getInstance().updateGame(id, gp);	
	}
	
	/**
	 * Singleton instance of user service
	 * @return GameService
	 */
	public static GameMapper getInstance() {
		if (instance == null) {
			instance = new GameMapper();
		}
		return instance;
	}

}
