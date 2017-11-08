package org.soen387.services;

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
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}
	
	public String updateLastLogin(User user, String lastLogin) {
		String oldLogin = UserTDG.getInstance().updateLastLogin(user, lastLogin);
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
