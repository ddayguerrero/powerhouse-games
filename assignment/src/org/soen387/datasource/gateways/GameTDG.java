package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.soen387.datasource.DatabaseConnection;
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
	
	public ArrayList<Game> getGameByTitle(String title) {
		final String sql = "SELECT * FROM game WHERE title LIKE ?";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(sql);
			ps.setString(1, "%" + title + "%");
			ResultSet rs = ps.executeQuery();
			return gameMapper.mapMultiple(rs);
		} catch (SQLException se) {
			System.out.println("Failed to execute query: " + se.getMessage());
		}
		
		ResultSet result = executeQuery(sql);
		return gameMapper.mapMultiple(result);
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
