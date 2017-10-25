package org.soen387.services;

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
	 * @return
	 */
	public User register() {
		// TODO
		return null;
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
