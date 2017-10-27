package org.soen387.services;

import java.util.ArrayList;

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
	 * @param title
	 * @return list of Games
	 */
	public ArrayList<Game> getGameByTitle(String title) {
		return GameTDG.getInstance().getGameByTitle(title);
	}
	
	/**
	 * Initiates call to retrieve a list of games by title
	 * @param title
	 * @return list of Games
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
