package com.soen387.datascraper;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.soen387.datascraper.data.Boxart;
import com.soen387.datascraper.data.Game;
import com.soen387.datascraper.data.GameResponse;
import com.soen387.datascraper.data.Genre;
import com.soen387.datascraper.data.Genres;
import com.soen387.datascraper.data.PlatformGamesResponse;

import retrofit2.Call;

public class App {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//	private static final String DB_CONNECTION = "jdbc:mysql://localhost/soen387";
	private static final String DB_CONNECTION = "jdbc:mysql://0.0.0.0/soen387";
	private static final String DB_USER = "dramos";
//	private static final String DB_PASSWORD = "321zealot21";
	private static final String DB_PASSWORD = "password";
	
	public static void main(String[] args) throws IOException, ParseException {
		Controller ctrl = new Controller();
		List<Game> games = new ArrayList<Game>();
		final int x360 = 15;
		final int ps3 = 12;
		final int wii = 9;
		
		Call<PlatformGamesResponse> platformGameCall = ctrl.getPlatformGames(x360);
		PlatformGamesResponse platformGameResp = platformGameCall.execute().body();
		List<Game> x360games = platformGameResp.getList().subList(0, 20);
		
		Call<GameResponse> game0 = ctrl.fetchGameInfo(x360games.get(0).getId());
		GameResponse gameResp = game0.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game1 = ctrl.fetchGameInfo(x360games.get(1).getId());
		gameResp = game1.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game2 = ctrl.fetchGameInfo(x360games.get(2).getId());
		gameResp = game2.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game3 = ctrl.fetchGameInfo(x360games.get(3).getId());
		gameResp = game3.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game4 = ctrl.fetchGameInfo(x360games.get(4).getId());
		gameResp = game4.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game5 = ctrl.fetchGameInfo(x360games.get(5).getId());
		gameResp = game5.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game6 = ctrl.fetchGameInfo(x360games.get(6).getId());
		gameResp = game6.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game7 = ctrl.fetchGameInfo(x360games.get(7).getId());
		gameResp = game7.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game8 = ctrl.fetchGameInfo(x360games.get(8).getId());
		gameResp = game8.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game9 = ctrl.fetchGameInfo(x360games.get(9).getId());
		gameResp = game9.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game10 = ctrl.fetchGameInfo(x360games.get(10).getId());
		gameResp = game10.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game11 = ctrl.fetchGameInfo(x360games.get(11).getId());
		gameResp = game11.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game12 = ctrl.fetchGameInfo(x360games.get(12).getId());
		gameResp = game12.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game13 = ctrl.fetchGameInfo(x360games.get(13).getId());
		gameResp = game13.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game14 = ctrl.fetchGameInfo(x360games.get(14).getId());
		gameResp = game14.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game15 = ctrl.fetchGameInfo(x360games.get(15).getId());
		gameResp = game15.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game16 = ctrl.fetchGameInfo(x360games.get(16).getId());
		gameResp = game16.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game17 = ctrl.fetchGameInfo(x360games.get(17).getId());
		gameResp = game17.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game18 = ctrl.fetchGameInfo(x360games.get(18).getId());
		gameResp = game18.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game19 = ctrl.fetchGameInfo(x360games.get(19).getId());
		gameResp = game19.execute().body();
		games.add(gameResp.getGame());
		
		platformGameCall = ctrl.getPlatformGames(ps3);
		platformGameResp = platformGameCall.execute().body();
		List<Game> ps3games = platformGameResp.getList().subList(0, 20);
		
		Call<GameResponse> game20 = ctrl.fetchGameInfo(ps3games.get(0).getId());
		gameResp = game20.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game21 = ctrl.fetchGameInfo(ps3games.get(1).getId());
		gameResp = game21.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game22 = ctrl.fetchGameInfo(ps3games.get(2).getId());
		gameResp = game22.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game23 = ctrl.fetchGameInfo(ps3games.get(3).getId());
		gameResp = game23.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game24 = ctrl.fetchGameInfo(ps3games.get(4).getId());
		gameResp = game24.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game25 = ctrl.fetchGameInfo(ps3games.get(5).getId());
		gameResp = game25.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game26 = ctrl.fetchGameInfo(ps3games.get(6).getId());
		gameResp = game26.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game27 = ctrl.fetchGameInfo(ps3games.get(7).getId());
		gameResp = game27.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game28 = ctrl.fetchGameInfo(ps3games.get(8).getId());
		gameResp = game28.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game29 = ctrl.fetchGameInfo(ps3games.get(9).getId());
		gameResp = game29.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game30 = ctrl.fetchGameInfo(ps3games.get(10).getId());
		gameResp = game30.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game31 = ctrl.fetchGameInfo(ps3games.get(11).getId());
		gameResp = game31.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game32 = ctrl.fetchGameInfo(ps3games.get(12).getId());
		gameResp = game32.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game33 = ctrl.fetchGameInfo(ps3games.get(13).getId());
		gameResp = game33.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game34 = ctrl.fetchGameInfo(ps3games.get(14).getId());
		gameResp = game34.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game35 = ctrl.fetchGameInfo(ps3games.get(15).getId());
		gameResp = game35.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game36 = ctrl.fetchGameInfo(ps3games.get(16).getId());
		gameResp = game36.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game37 = ctrl.fetchGameInfo(ps3games.get(17).getId());
		gameResp = game37.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game38 = ctrl.fetchGameInfo(ps3games.get(18).getId());
		gameResp = game38.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game39 = ctrl.fetchGameInfo(ps3games.get(19).getId());
		gameResp = game39.execute().body();
		games.add(gameResp.getGame());
		
		platformGameCall = ctrl.getPlatformGames(wii);
		platformGameResp = platformGameCall.execute().body();
		List<Game> wiigames = platformGameResp.getList().subList(0, 20);
		
		Call<GameResponse> game40 = ctrl.fetchGameInfo(ps3games.get(0).getId());
		gameResp = game40.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game41 = ctrl.fetchGameInfo(wiigames.get(1).getId());
		gameResp = game41.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game42 = ctrl.fetchGameInfo(wiigames.get(2).getId());
		gameResp = game42.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game43 = ctrl.fetchGameInfo(wiigames.get(3).getId());
		gameResp = game43.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game44 = ctrl.fetchGameInfo(wiigames.get(4).getId());
		gameResp = game44.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game45 = ctrl.fetchGameInfo(wiigames.get(5).getId());
		gameResp = game45.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game46 = ctrl.fetchGameInfo(wiigames.get(6).getId());
		gameResp = game46.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game47 = ctrl.fetchGameInfo(wiigames.get(7).getId());
		gameResp = game47.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game48 = ctrl.fetchGameInfo(wiigames.get(8).getId());
		gameResp = game48.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game49 = ctrl.fetchGameInfo(wiigames.get(9).getId());
		gameResp = game49.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game50 = ctrl.fetchGameInfo(wiigames.get(10).getId());
		gameResp = game50.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game51 = ctrl.fetchGameInfo(wiigames.get(11).getId());
		gameResp = game51.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game52 = ctrl.fetchGameInfo(wiigames.get(12).getId());
		gameResp = game52.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game53 = ctrl.fetchGameInfo(wiigames.get(13).getId());
		gameResp = game53.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game54 = ctrl.fetchGameInfo(wiigames.get(14).getId());
		gameResp = game54.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game55 = ctrl.fetchGameInfo(wiigames.get(15).getId());
		gameResp = game55.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game56 = ctrl.fetchGameInfo(wiigames.get(16).getId());
		gameResp = game56.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game57 = ctrl.fetchGameInfo(wiigames.get(17).getId());
		gameResp = game57.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game58 = ctrl.fetchGameInfo(wiigames.get(18).getId());
		gameResp = game58.execute().body();
		games.add(gameResp.getGame());
		
		Call<GameResponse> game59 = ctrl.fetchGameInfo(wiigames.get(19).getId());
		gameResp = game59.execute().body();
		games.add(gameResp.getGame());
		
		try {
			initTables();
			populateTables(games);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void populateTables(List<Game> games) throws SQLException, ParseException {
		Connection dbConnection = null;
		String populateGamesQuery = "INSERT INTO Game " +
		"(game_id, title, game_description, console, num_players, coop," +
		"genre, release_date, developer, publisher, front_box_art, back_box_art, price, discount) " +
		"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			dbConnection = getDBConnection();
			for(Game g: games) {
				PreparedStatement preparedStatement = dbConnection.prepareStatement(populateGamesQuery);
				preparedStatement.setInt(1, g.getId());
				preparedStatement.setString(2, g.getGameTitle());
				preparedStatement.setString(3, g.getDescription());
				preparedStatement.setString(4, g.getConsole());
				
				String numPlayers = "1";
				if(g.getPlayers() != null) {
					numPlayers = g.getPlayers();
				}
				
				preparedStatement.setString(5, numPlayers);
				
				Boolean hasCoop = false;
				if (g.getCoop().equals("Yes")) {
					hasCoop = true;
				}
				preparedStatement.setBoolean(6, hasCoop);
				
				Genres genresObj = g.getGenres();
				if(genresObj != null) {
					List<Genre> genres =  genresObj.getList();
					if(!genres.isEmpty()) {
						preparedStatement.setString(7, genres.get(0).getGenre()); // TODO
					} else {
						preparedStatement.setString(7, null);
					}
				} else {
					preparedStatement.setString(7, null);
				}
				
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				Date releaseDate = new Date(formatter.parse(g.getReleaseDate()).getTime());
				preparedStatement.setDate(8, releaseDate);
				preparedStatement.setString(9, g.getDeveloper());
				preparedStatement.setString(10, g.getPublisher());
				
				final String gamesDBURL= "http://thegamesdb.net/banners/";
				final String defaultCoverArtURL = "https://static1.squarespace.com/static/52f29ad2e4b02d1f9d476561/5390b6b6e4b052006822dd29/5390b747e4b06374d7eb79fb/1401993081305/noCoverArt.gif?format=500w";
				preparedStatement.setString(11, defaultCoverArtURL);
				preparedStatement.setString(12, defaultCoverArtURL);
				
				if(g.getImages().getList().size() > 0) {
					for(Boxart b: g.getImages().getList()) {
						String side = b.getSide();
						if(side.equals("front")) {
							String frontBoxArtURL = gamesDBURL.concat(b.getBoxart());
							preparedStatement.setString(11, frontBoxArtURL);
						}
						if(side.equals("back")) {
							String backBoxArtURL = gamesDBURL.concat(b.getBoxart());
							preparedStatement.setString(12, backBoxArtURL);
						}
					}
				}
				
				BigDecimal random = generateRandomBigDecimalFromRange(
				    new BigDecimal(12.99).setScale(2, BigDecimal.ROUND_HALF_UP),
				    new BigDecimal(79.99).setScale(2, BigDecimal.ROUND_HALF_UP)
				);
				
				System.out.println(random);
				
				preparedStatement.setBigDecimal(13, random);
				preparedStatement.setBigDecimal(14, new BigDecimal(0));
				int count = preparedStatement.executeUpdate();
			}
			System.out.println("All games inserted!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	
	public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
	    BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    BigDecimal finalBD = new BigDecimal(Math.ceil(randomBigDecimal.doubleValue() * 20) / 20);
	    return finalBD.setScale(2,BigDecimal.ROUND_HALF_UP);
	}

	private static void initTables() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String createGameTableSQL = "CREATE TABLE IF NOT EXISTS Game(" + 
				"id SERIAL NOT NULL PRIMARY KEY," + 
				"game_id INT ,"+
				"title TINYTEXT NOT NULL," + 
				"game_description TEXT ," + 
				"console TEXT ," + 
				"num_players TEXT ," + 
				"coop BOOLEAN ," + 
				"genre TEXT ," + 
				"release_date DATE ," + 
				"developer TEXT ," + 
				"publisher TEXT ," + 
				"front_box_art TEXT ," + 
				"back_box_art TEXT ," + 
				"price DECIMAL(4,2) ," + 
				"discount DECIMAL(4,2)" + 
				");";
		
		String createUserTableSQL = "CREATE TABLE IF NOT EXISTS User(" + 
				"user_id SERIAL NOT NULL PRIMARY KEY," + 
				"password TINYTEXT NOT NULL," + 
				"firstname TINYTEXT NOT NULL," + 
				"lastname TINYTEXT NOT NULL," + 
				"email TINYTEXT NOT NULL," + 
				"address1 TINYTEXT ," + 
				"address2 TINYTEXT ," + 
				"city TINYTEXT ," + 
				"province VARCHAR(2) ," + 
				"postalcode VARCHAR(6) ," + 
				"country TINYTEXT ," + 
				"credit_card_type TINYTEXT," + 
				"credit_card_number VARCHAR(16) ," + 
				"credit_card_cvv VARCHAR(3)," + 
				"credit_card_expiry DATE," + 
				"last_login DATE" + 
				");";
		
		String createCommentsTableSQL = "CREATE TABLE IF NOT EXISTS Comments(" + 
				"comment_id SERIAL NOT NULL PRIMARY KEY," + 
				"user_id INT REFERENCES Game(id)," + 
				"game_id INT REFERENCES User(id)," + 
				"comment_date DATE NOT NULL," + 
				"comment_details DATE NOT NULL," + 
				"ratings INT" + 
				");";
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.execute(createGameTableSQL);
			System.out.println("Table GAME is created!");
			statement.execute(createUserTableSQL);
			System.out.println("Table USER is created!");
			statement.execute(createCommentsTableSQL);
			System.out.println("Table COMMENTS is created!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
			dbConnection = DriverManager.getConnection(
					DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbConnection;
		
		
	}

}
