package org.soen387.datasource.mappers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.soen387.datasource.gateways.FavoritesTDG;
import org.soen387.datasource.gateways.UserTDG;
import org.soen387.domain.Game;
import org.soen387.domain.User;
import org.soen387.payloads.UserPayload;

/**
 * User Service
 * @author Darrel-Day Guerrero 27352409
 *
 */
public class UserMapper {

	private static UserMapper instance = null;
	
	public UserMapper() {
		super();
	}
	
	/**
	 * Register new user
	 * @param newUser 
	 * @return
	 */
	public int register(UserPayload newUser) {
		int result = UserTDG.getInstance().createUser(newUser);
		return result;
	}
	
	/**
	 * Set a new temporary password with 24H expiration
	 * @return
	 */
	public int setTemporaryPassword(int id, String tempPassword, Timestamp expiry) {
		int result = UserTDG.getInstance().updatePassword(id, tempPassword, expiry);
		return result;
	}
	
	/**
	 * Fetch user by ID
	 * @return
	 */
	public User fetchUser(int userId) {
		User user = UserTDG.getInstance().getUserById(userId);
		return user != null ? user : null;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		User user = UserTDG.getInstance().getUserByEmail(email);
		return (user != null) ? user : null;
	}
	
	/**
	 * Authenticate user
	 * @return
	 */
	public User find(String email, String password) {
		User user = UserTDG.getInstance().getUserByEmail(email);
		if (user != null) {
			if(user.getPassword().equals(password)) {
				return user;
			}
			else {
				System.out.println("Logins : " + user.getFailed_logins());
				if(user.getFailed_logins() < 3) {
					int result = UserTDG.getInstance().updateFailedLogin(user, user.getFailed_logins() + 1);
					return null;
				} else {
					user = UserTDG.getInstance().lockUser(user);
					return user;
				}
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Fetch all users
	 * @return
	 */
	public ArrayList<User> getAllUsers() {
		return UserTDG.getInstance().getAllUsers();
	}
	
	/**
	 * 
	 * @param userId
	 * @param locked
	 * @return
	 */
	public User updateUser(int userId, int locked) {
		User user = UserTDG.getInstance().getUserById(userId);
		if (user != null) {
			if (locked == 0) {
				user = UserTDG.getInstance().unlockUser(user);
			} else {
				user = UserTDG.getInstance().lockUser(user);
			}
			return user;
		} else {
			return null;
		}
	}
	
	/**
	 * Mark game as favorite for user
	 * @param userid - User Id
	 * @param gameId - Game Id
	 */
	public void addFavorite(int userId, String gameId) {
		FavoritesTDG.getInstance().addToFavorites(userId, gameId);
	}
	
	/**
	 * Remove game as favorite for user
	 * @param userid - User Id
	 * @param gameId - Game Id
	 */
	public void removeFavorite(int userId, String gameId) {
		FavoritesTDG.getInstance().removeFromFavorites(userId, gameId);
	}
	
	public List<Game> getFavorites(int userId) {
		return FavoritesTDG.getInstance().getUserFavoriteGames(userId);
	}
	
	/**
	 * Update last login of user
	 * @param user - User
	 * @param lastLogin - Timestamp
	 * @return Old login
	 */
	public Timestamp updateLastLogin(User user, Timestamp lastLogin) {
		Timestamp oldLogin = UserTDG.getInstance().updateLastLogin(user, lastLogin);
		return oldLogin;
		
	}
	
	public void updateProfile(int id, UserPayload updatedUserInfo) {
		UserTDG.getInstance().updateUserProfile(id, updatedUserInfo);
	}
	
	/**
	 * Singleton instance of user service
	 * @return
	 */
	public static UserMapper getInstance () {
		if (instance == null) {
			instance = new UserMapper();
		}
		return instance;
	}
}
