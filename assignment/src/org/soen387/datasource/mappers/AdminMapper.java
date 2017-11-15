package org.soen387.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.soen387.domain.Admin;

public class AdminMapper implements DataMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs) {
		ArrayList<Admin> admins = mapMultiple(rs);
		if(admins.isEmpty()) {
			return null;
		}
		return admins.get(0);
	}

	@Override
	public ArrayList<Admin> mapMultiple(ResultSet rs) {
		final ArrayList<Admin> admins = new ArrayList<Admin>();
		
		if (rs != null) {
			try {
				while(rs.next()) {
					final Admin admin = new Admin();
					admin.setPassword(rs.getString("password"));
					admin.setEmail(rs.getString("email"));
					admins.add(admin);
				}
			} catch (SQLException se) {
				System.out.println("Error in mapping multiple Users: " + se.getMessage());
			}
		}
		return admins;
	}

}
