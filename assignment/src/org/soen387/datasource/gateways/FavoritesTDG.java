package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.soen387.datasource.DatabaseConnection;

public class FavoritesTDG {
	private static FavoritesTDG instance = null;
	
	private FavoritesTDG() {
	}
	
	public static FavoritesTDG getInstance() {
		if (FavoritesTDG.instance == null) {
			instance = new FavoritesTDG();
		}
		return instance;
	}

	/**
	 * Insert favorite game for user
	 * @param userId
	 * @param gameId
	 */
	public void addToFavorites(int userId, String gameId) {
		final String insertFavoriteQuery = "INSERT INTO Favorites(user_id, game_id) VALUES (?,?)";
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(insertFavoriteQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, Integer.parseInt(gameId));
			int result = preparedStatement.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Failed to insert favorite: " + se.getMessage());
		}
		finally {
			DatabaseConnection.clearConnection();
		}
	}

	public void removeFromFavorites(int userId, String gameId) {
		final String deleteFavoriteQuery = "DELETE FROM Favorites WHERE user_id = ? AND game_id ?";
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(deleteFavoriteQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, Integer.parseInt(gameId));
			int result = preparedStatement.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Failed to remove favorite: " + se.getMessage());
		}
		finally {
			DatabaseConnection.clearConnection();
		}	
	}
}
