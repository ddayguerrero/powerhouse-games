package org.soen387.services;

import org.soen387.beans.UserBean;
import org.soen387.datasource.gateways.UserTDG;
import org.soen387.domain.User;

/**
 * 
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
	public User login() {
		// TODO
		return null;
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
