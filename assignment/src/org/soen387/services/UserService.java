package org.soen387.services;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.soen387.beans.UserBean;
import org.soen387.datasource.gateways.UserTDG;
import org.soen387.domain.User;

/**
 * User Service
 * @author Darrel-Day Guerrero 27352409
 *
 */
public class UserService {

	private static UserService instance = null;
	
	public UserService() {
		super();
	}
	
	/**
	 * Register new user
	 * @param newUser 
	 * @return
	 */
	public int register(UserBean newUser) {
		int result = UserTDG.getInstance().createUser(newUser);
		return result;
	}
	
	/**
	 * Fetch user by ID
	 * @return
	 */
	public User fetchUser() {
		// TODO
		return null;
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
	 * Update last login of user
	 * @param user - User
	 * @param lastLogin - Timestamp
	 * @return Old login
	 */
	public Timestamp updateLastLogin(User user, Timestamp lastLogin) {
		Timestamp oldLogin = UserTDG.getInstance().updateLastLogin(user, lastLogin);
		return oldLogin;
		
	}
	
	/**
	 * Singleton instance of user service
	 * @return
	 */
	public static UserService getInstance () {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

}
