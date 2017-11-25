package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.soen387.business.ShoppingCart;
import org.soen387.datasource.DatabaseConnection;
import org.soen387.domain.Game;
import org.soen387.domain.User;

public class InvoiceTDG {

	private static InvoiceTDG instance = null;

	private InvoiceTDG() {
		
	}
	
	public static InvoiceTDG getInstance() {
		if (InvoiceTDG.instance == null) {
			instance = new InvoiceTDG();
		}
		return instance;
	}
	
	/**
	 * Insert invoice for user
	 * @param cart - User shopping cart
	 * @param user - User information
	 * @return 1 - success or 0 - fail
	 */
	public int insertInvoice(User user, ShoppingCart cart) throws SQLException {
		final String insertInvoiceSql = "INSERT INTO INVOICE (SALE_DATE, CLIENT_ID, NET_PRICE, TAX, TOTAL) VALUES (?, ?, ?, ?, ?)";
		int result = 0;
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(insertInvoiceSql);
			Calendar cal = Calendar.getInstance();
			ps.setTimestamp(1, new Timestamp(cal.getTimeInMillis()));
			ps.setInt(2, user.getUserid());
			ps.setBigDecimal(3, cart.getSubTotal());
			ps.setBigDecimal(4, cart.getCalculatedTaxes());
			ps.setBigDecimal(5, cart.getGrandTotal());
			result = ps.executeUpdate();
		} 
		catch (SQLException se) {
			System.out.println("Failed to execute insertInvoiceSql query: " + se.getMessage());
		} 
		finally {
			DatabaseConnection.clearConnection();
		}
		return result;
	}
}
