package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.soen387.beans.UserBean;
import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.mappers.UserMapper;
import org.soen387.domain.User;

public class UserTDG {

	private UserMapper userMapper;
	private static UserTDG instance = null;
	
	private UserTDG() {
		this.userMapper = new UserMapper();
	}
	
	public static UserTDG getInstance() {
		if (UserTDG.instance == null) {
			instance = new UserTDG();
		}
		return instance;
	}
	
	/**
	 * Register a new user
	 * @param newUser - New User
	 * @return 1 success or -1 failure
	 */
	public int createUser(UserBean newUser) {
		final String insertUserQuery = "INSERT INTO User " +
				"(password, firstname, lastname, email, address1, address2," +
				"city, postalcode, province, country, last_login) " +
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		int count = -1;
		try {
			PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(insertUserQuery);
			preparedStatement.setString(1, newUser.getPassword());
			preparedStatement.setString(2, newUser.getFirstName());
			preparedStatement.setString(3, newUser.getLastName());
			preparedStatement.setString(4, newUser.getEmail());
			preparedStatement.setString(5, newUser.getAddress1());
			preparedStatement.setString(6, newUser.getAddress2());
			preparedStatement.setString(7, newUser.getCity());
			preparedStatement.setString(8, newUser.getPostalCode());
			preparedStatement.setString(9, newUser.getProvince());
			preparedStatement.setString(10, newUser.getCountry());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Calendar cal = Calendar.getInstance(); 
			preparedStatement.setTimestamp(11, new Timestamp(cal.getTimeInMillis()));
			count = preparedStatement.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Failed to create user: " + se.getMessage());
		}
		return count;
	}
	
	public ArrayList<User> getAllUsers() {
		final String selectUserEmailQuery = "SELECT * FROM User;";
		ResultSet resultSet = executeQuery(selectUserEmailQuery);
		return userMapper.mapMultiple(resultSet);
	}
	
	/**
	 * Retrieve user by email
	 * @param email - User's email
	 * @return User
	 */
	public User getUserByEmail(String email) {
		final String selectUserEmailQuery = "SELECT * FROM User WHERE email='" + email + "';";
		ResultSet resultSet = executeQuery(selectUserEmailQuery);
		return userMapper.mapRow(resultSet);
	}
	
	/**
	 * Retrieve user by id
	 * @param id - User's id
	 * @return User
	 */
	public User getUserById(int id) {
		final String selectUserEmailQuery = "SELECT * FROM User WHERE user_id = ? ;";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(selectUserEmailQuery);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return userMapper.mapRow(rs);
		}
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Failed to retrieve user: " + se.getMessage());
			return null;
		}
	}
	
	/**
	 * Update last login date of user
	 * @param user - User
	 * @param lastLogin - Last login of user
	 * @return Timestamp of last login
	 */
	public Timestamp updateLastLogin(User user, Timestamp lastLogin) {
		final String getOldLoginQuery = "SELECT last_login FROM User WHERE user_id = ?";
		final String updateLastLoginQuery = "UPDATE user SET last_login = ? WHERE user_id = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(getOldLoginQuery);
			ps.setInt(1, user.getUserid());
			ResultSet rs = ps.executeQuery();
			rs.next();
			Timestamp oldLogin = rs.getTimestamp("last_login");
			
			ps = DatabaseConnection.getInstance().prepareStatement(updateLastLoginQuery);
			ps.setTimestamp(1, lastLogin);
			ps.setInt(2, user.getUserid());
			int result = ps.executeUpdate();
			return oldLogin;
		}
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Failed to create user: " + se.getMessage());
			return null;
		}
	}
	
	/**
	 * Update failed login attempts of user
	 * @param user - User
	 * @param count - Last login of user
	 * @return Amount of login attempts
	 */
	public int updateFailedLogin(User user, int count) {
		final String incrementFailLoginAttemptQuery = "UPDATE user SET failed_logins = ? WHERE user_id = ?";
		int result = -1;
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(incrementFailLoginAttemptQuery);
			ps.setInt(1, count);
			ps.setInt(2, user.getUserid());
			result = ps.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Failed to increment failed login: " + se.getMessage());
		}
		return result;
		
	}
	
	/**
	 * Lock user access
	 * @param user - User
	 * @return
	 */
	public User lockUser(User user) {
		final String lockUserQuery = "UPDATE user SET locked = 1 WHERE user_id = ?";
		int result = -1;
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(lockUserQuery);
			ps.setInt(1, user.getUserid());
			result = ps.executeUpdate();
			return getUserByEmail(user.getEmail());
			
		} catch (SQLException se) {
			System.out.println("Failed to lock user: " + se.getMessage());
		}
		return null;
	}
	
	/**
	 * Change Update password
	 * @param tempPassword - Password
	 * @return
	 */
	public int updatePassword(int id, String password, Timestamp expiry) {
		final String lockUserQuery = "UPDATE user SET password = ?, password_expiry = ? WHERE user_id = ?";
		int result = -1;
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(lockUserQuery);
			ps.setString(1, password);
			ps.setTimestamp(2, expiry);
			ps.setInt(3, id);
			result = ps.executeUpdate();
			return result;
			
		} catch (SQLException se) {
			System.out.println("Failed to update password user: " + se.getMessage());
		}
		return result;
	}
	
	/**
	 * Unlock user access
	 * @param user - User
	 * @return
	 */
	public User unlockUser(User user) {
		final String unlockUserQuery = "UPDATE user SET locked = 0, failed_logins = 0 WHERE user_id = ?";
		int result = -1;
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(unlockUserQuery);
			ps.setInt(1, user.getUserid());
			result = ps.executeUpdate();
			return getUserByEmail(user.getEmail());
			
		} catch (SQLException se) {
			System.out.println("Failed to lock user: " + se.getMessage());
		}
		return null;
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
