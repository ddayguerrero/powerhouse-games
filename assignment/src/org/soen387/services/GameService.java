package org.soen387.services;

import java.util.ArrayList;

import org.soen387.datasource.gateways.GameTDG;
import org.soen387.domain.Game;

public class GameService {
	
	public static GameService instance = null;
	
	private GameService() { }
	
	public ArrayList<Game> getGameByTitle(String title) {
		return GameTDG.getInstance().getGameByTitle(title);
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
