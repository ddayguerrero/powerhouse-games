package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.mappers.GameMapper;
import org.soen387.domain.Game;

public class FavoritesTDG {
	private static FavoritesTDG instance = null;
	private GameMapper gameMapper;
	
	private FavoritesTDG() {
		this.gameMapper = new GameMapper();
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

	/**
	 * Delete favorite game for user
	 * @param userId
	 * @param gameId
	 */
	public void removeFromFavorites(int userId, String gameId) {
		final String deleteFavoriteQuery = "DELETE FROM Favorites WHERE user_id = ? AND game_id ?";
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(deleteFavoriteQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, Integer.parseInt(gameId));
			int result = preparedStatement.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Failed to remove favorite: " + se.getMessage());
		} finally {
			DatabaseConnection.clearConnection();
		}	
	}

	public List<Game> getUserFavoriteGames(int userId) {
		final String selectUserFavoriteGamesQuery = "SELECT game_id FROM Favorites WHERE user_id = ?";
		final String selectGamesQuery = "SELECT * FROM Game WHERE id = ?";
		List<Game> favorites = new ArrayList<Game>();
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(selectUserFavoriteGamesQuery);
			ps.setInt(1, userId);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				System.out.println("FAVgameid " +result.getString("game_id"));
				ps = DatabaseConnection.getInstance().prepareStatement(selectGamesQuery);
				ps.setString(1, result.getString("game_id"));
				ResultSet gameResultSet = ps.executeQuery();
				Game fav = gameMapper.mapRow(gameResultSet);
				System.out.println("Fav : " + fav);
				favorites.add(fav);
			}
			System.out.println("Favorites : " + favorites);
			return favorites;
		} catch (SQLException se) {
			System.out.println("Failed to retrieve favorites: " + se.getMessage());
			return null;
		} finally {
			DatabaseConnection.clearConnection();
		}
	}
}
