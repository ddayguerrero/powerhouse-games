package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.DatabaseQueryBuilder;
import org.soen387.datasource.orm.GameORM;
import org.soen387.domain.Game;
import org.soen387.payloads.AdvancedSearchPayload;
import org.soen387.payloads.GamePayload;

/**
 * Game Table Data Gateway
 * @author Darrel Guerrero - 27352409
 *
 */
public class GameTDG {

	private GameORM gameMapper;
	private static GameTDG instance = null;

	private GameTDG() {
		this.gameMapper = new GameORM();
	}
	
	public static GameTDG getInstance() {
		if (GameTDG.instance == null) {
			instance = new GameTDG();
		}
		return instance;
	}
	
	/**
	 * Retrieve all games
	 * @return List of full game inventory
	 */
	public ArrayList<Game> getAllGames() {
		final String getAllGames= "SELECT * FROM game";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(getAllGames);
			ResultSet rs = ps.executeQuery();
			return gameMapper.mapMultiple(rs);
		} catch (SQLException se) {
			System.out.println("Failed to execute getAllGames query: " + se.getMessage());
		}
		finally {
			DatabaseConnection.clearConnection();
		}
		return null;
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
		finally {
			DatabaseConnection.clearConnection();
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
		} finally {
			DatabaseConnection.clearConnection();
		}
		return null;
	}
	
	/**
	 * Retrieve games by advanced search
	 * @param advSearchBean - Custom search query
	 * @return Matching games
	 */
	public ArrayList<Game> getGamesByAdvanced(AdvancedSearchPayload advSearchBean) {
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
		} finally {
	    		DatabaseConnection.clearConnection();
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
		} finally {
	    		DatabaseConnection.clearConnection();
	    }
		return null;
	}
	
	/**
	 * Add new game to inventory
	 * @param gamePayload - Game Information
	 * @return
	 */
	public int insertGame(GamePayload gamePayload) {
		final String insertGameQuery = "INSERT INTO Game " +
				"(game_id, title, game_description, console, num_players, coop," +
				"genre, release_date, developer, publisher, front_box_art, back_box_art, price, discount) " +
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		int count = -1;
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(insertGameQuery);
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, gamePayload.getTitle());
			preparedStatement.setString(3, gamePayload.getDescription());
			preparedStatement.setString(4, gamePayload.getConsole());
			preparedStatement.setString(5, gamePayload.getPlayers());
			preparedStatement.setBoolean(6, gamePayload.isCoop());
			preparedStatement.setString(7, gamePayload.getGenre());
			preparedStatement.setDate(8, gamePayload.getRelease());
			preparedStatement.setString(9, gamePayload.getDeveloper());
			preparedStatement.setString(10, gamePayload.getPublisher());
			preparedStatement.setString(11, gamePayload.getFront_cover());
			preparedStatement.setString(12, gamePayload.getBack_cover());
			preparedStatement.setBigDecimal(13, gamePayload.getPrice());
			preparedStatement.setBigDecimal(14, gamePayload.getDiscount());
			count = preparedStatement.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Failed to insert game: " + se.getMessage());
		} finally {
	    		DatabaseConnection.clearConnection();
	    }
		return count;
	}
	
	/**
	 * Update game from inventory
	 * @param id - Game Id
	 * @param game - Game Information
	 * @return
	 */
	public void updateGame(int id, GamePayload game) {
		final String updateGameQuery = "UPDATE Game SET title = ?, game_description = ?, console = ?, num_players = ?, coop = ?, genre = ?, release_date = ?, developer = ?, publisher = ?, " +
				"front_box_art = ?, back_box_art = ?, price = ?, discount = ? WHERE id = ?;";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(updateGameQuery);
			ps.setString(1, game.getTitle());
			ps.setString(2, game.getDescription());
			ps.setString(3, game.getConsole());
			ps.setString(4, game.getPlayers());
			ps.setBoolean(5, game.isCoop());
			ps.setString(6, game.getGenre());
			ps.setDate(7, game.getRelease());
			ps.setString(8, game.getDeveloper());
			ps.setString(9, game.getPublisher());
			ps.setString(10, game.getFront_cover());
			ps.setString(11, game.getBack_cover());
			ps.setBigDecimal(12, game.getPrice());
			ps.setBigDecimal(13, game.getDiscount());
			ps.setInt(14, id);
			int rs = ps.executeUpdate();
		} catch(SQLException e){
			System.out.println("Failed to update game: " + e.getMessage());
        } finally {
        		DatabaseConnection.clearConnection();
        }
		
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