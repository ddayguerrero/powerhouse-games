package org.soen387.datasource.gateways;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.orm.AdminORM;
import org.soen387.domain.Admin;

public class AdminTDG {
	
	private AdminORM adminMapper;
	private static AdminTDG instance = null;
	
	private AdminTDG() {
		this.adminMapper = new AdminORM();
	}
	
	public static AdminTDG getInstance() {
		if (AdminTDG.instance == null) {
			instance = new AdminTDG();
		}
		return instance;
	}
	
	/**
	 * Retrieve admin by email
	 * @param email - User's email
	 * @return Admin
	 */
	public Admin getUserByEmail(String email) {
		final String selectUserEmailQuery = "SELECT * FROM Admin WHERE email='" + email + "';";
		ResultSet resultSet = executeQuery(selectUserEmailQuery);
		return adminMapper.mapRow(resultSet);
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
