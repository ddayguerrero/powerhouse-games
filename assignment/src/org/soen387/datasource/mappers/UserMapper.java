package org.soen387.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.soen387.domain.User;

public class UserMapper implements DataMapper<User>{

	@Override
	public User mapRow(ResultSet rs) {
		ArrayList<User> users = mapMultiple(rs);
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public ArrayList<User> mapMultiple(ResultSet rs) {
		final ArrayList<User> users = new ArrayList<User>();
		
		if (rs != null) {
			try {
				while(rs.next()) {
					final User user = new User();
					user.setUserid(rs.getInt("user_id"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setAddress1(rs.getString("address1"));
					user.setAddress2(rs.getString("address2"));
					user.setCity(rs.getString("city"));
					user.setProvince(rs.getString("province"));
					user.setPostalCode(rs.getString("postalcode"));
					user.setCountry(rs.getString("country"));
					user.setFailed_logins(rs.getInt("failed_logins"));
					user.setLocked(rs.getBoolean("locked"));
					user.setLastLogin(rs.getTimestamp("last_login"));
					users.add(user);
				}
			} catch (SQLException se) {
				System.out.println("Error in mapping multiple Users: " + se.getMessage());
			}
		}
		return users;
	}

}
