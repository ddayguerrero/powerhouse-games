package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.soen387.beans.AdvancedSearchBean;
import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.DatabaseQueryBuilder;
import org.soen387.datasource.mappers.GameMapper;
import org.soen387.domain.Game;

/**
 * Game Table Data Gateway
 * @author Darrel Guerrero - 27352409
 *
 */
public class GameTDG {

	private GameMapper gameMapper;
	private static GameTDG instance = null;

	private GameTDG() {
		this.gameMapper = new GameMapper();
	}
	
	public static GameTDG getInstance() {
		if (GameTDG.instance == null) {
			instance = new GameTDG();
		}
		return instance;
	}
	
	/**
	 * Retrieve games by title
	 * @param title - Title query
	 * @return List of matching games
	 */
	public ArrayList<Game> getGamesByTitle(String title) {
		final String getGameByTitle = "SELECT * FROM game WHERE title LIKE ?";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(getGameByTitle);
			ps.setString(1, "%" + title + "%");
			ResultSet rs = ps.executeQuery();
			return gameMapper.mapMultiple(rs);
		} catch (SQLException se) {
			System.out.println("Failed to execute getGameByTitle query: " + se.getMessage());
		}
		return null;
	}
	
	/**
	 * Retrieve game by id
	 * @param id - Id query
	 * @return Corresponding game
	 */
	public Game getGameById(int id) {
		final String getGameByIdSql = "SELECT * FROM game WHERE id= ?";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(getGameByIdSql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return gameMapper.mapRow(rs);
		} catch (SQLException se) {
			System.out.println("Failed to execute getGameById query: " + se.getMessage());
		}
		return null;
	}
	
	/**
	 * Retrieve games by advanced search
	 * @param advSearchBean - Custom search query
	 * @return Matching games
	 */
	public ArrayList<Game> getGamesByAdvanced(AdvancedSearchBean advSearchBean) {
		try {
			Map<String, Object> params = new HashMap<>();
			if(!advSearchBean.getTitle().isEmpty()) {
				params.put("title LIKE ", advSearchBean.getTitle());
			}
			if(!advSearchBean.getPublisher().isEmpty()) {
				params.put("publisher LIKE ", advSearchBean.getPublisher());
			}
			if(!advSearchBean.getConsole().equals("Console")) {
				params.put("console = ", advSearchBean.getConsole());
			}
			if(!advSearchBean.getYear().equals("Year")) {
				params.put("YEAR(release_date) = ", advSearchBean.getYear());
			}
			if(!advSearchBean.getGenre().isEmpty()) {
				params.put("genre LIKE ", advSearchBean.getGenre());
			}
			String advancedQuery = DatabaseQueryBuilder.generateSelect(params);
			System.out.println("Advanced query: " + advancedQuery);
			PreparedStatement ps = DatabaseQueryBuilder.prepareStatement(advancedQuery, params);
			ResultSet rs = ps.executeQuery();
			return gameMapper.mapMultiple(rs);
		} catch (SQLException se) {
			System.out.println("Failed to execute getGamesByAdvanced query: " + se.getMessage());
		}
		return null;
	}
	
	public ArrayList<Game> getDiscountedGames() {
		final String getDiscountedGames = "SELECT * FROM game WHERE discount != 0";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(getDiscountedGames);
			ResultSet rs = ps.executeQuery();
			return gameMapper.mapMultiple(rs);
		} catch (SQLException se) {
			System.out.println("Failed to execute getGameById query: " + se.getMessage());
		}
		return null;
	}
	
	protected ResultSet executeQuery(String query) {
		try {
			return DatabaseConnection.getInstance().createStatement().executeQuery(query);
		} catch (SQLException se) {
			System.out.println("Failed to execute query: " + se.getMessage());
		}
		return null;
	}
}