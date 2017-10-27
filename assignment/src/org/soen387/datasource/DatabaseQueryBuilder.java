package org.soen387.datasource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseQueryBuilder {

	/**
	 * Generate an SQL select statement based on the search term parameters
	 * @param parameters - Search parameters
	 * @return SQL query
	 */
	public static String generateSelect(Map<String, Object> parameters){
		String selectQueryResult = "SELECT * FROM game";
		boolean isFirst = true;
		
		for (String paramName : parameters.keySet()) {
			Object paramValue = parameters.get(paramName);
			if (paramValue != null) {
				if (isFirst) {
				selectQueryResult += " WHERE " + paramName + "?";
				isFirst = false;
				} else {
					selectQueryResult += " and " + paramName + "?";
				}
			}
		}
		selectQueryResult = selectQueryResult.concat(";");
		return selectQueryResult;
	}
	
	public static PreparedStatement prepareStatement(String query, Map<String, Object> parameters) {
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(query);
			int index = 1;
			for (String paramName : parameters.keySet()) {
				Object paramValue = parameters.get(paramName);
				if(paramValue != null) {
					if(paramName.equals("title LIKE ") || paramName.equals("publisher LIKE ")) {
						ps.setString(index, "%" + paramValue.toString() + "%");
						System.out.println(paramValue);
					} else {
						ps.setString(index, paramValue.toString());
						System.out.println(paramValue);
					}
				}
				index++;
			}
			return ps;
		} catch (SQLException se) {
			System.out.println("Failed to execute getGamesByAdvanced query: " + se.getMessage());
			se.printStackTrace();
		}
		return null;
	}
}
