package org.soen387.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.soen387.domain.Game;

public class GameMapper implements DataMapper<Game> {

	@Override
	public Game mapRow(ResultSet rs) {
		ArrayList<Game> games = mapMultiple(rs);
		if(games.isEmpty()) {
			return null;
		}
		return games.get(0);
	}

	@Override
	public ArrayList<Game> mapMultiple(ResultSet rs) {
		final ArrayList<Game> games = new ArrayList<Game>();
		
		if (rs!=null) {
			try {
				while (rs.next()) {
					final Game game = new Game();
					game.setGameid(rs.getInt("id"));
					game.setGameTitle(rs.getString("title"));
					game.setGameDescription(rs.getString("game_description"));
					game.setConsole(rs.getString("console"));
					game.setNumPlayers(rs.getString("num_players"));
					Boolean isCoop = rs.getInt("coop") == 1 ? true : false;
					game.setCoop(isCoop);
					game.setDeveloper(rs.getString("developer"));
					game.setPublisher(rs.getString("publisher"));
					game.setFront_box_art(rs.getString("front_box_art"));
					game.setBack_box_art(rs.getString("back_box_art"));
					game.setPrice(rs.getBigDecimal("price"));
					game.setDiscount(rs.getBigDecimal("discount"));
					games.add(game);
				}
			} catch (SQLException se) {
				System.out.println("Error in mapping multiple games: " + se.getMessage());
			}
		}
		return games;
	}

}
