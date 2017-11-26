package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.soen387.business.ShoppingCart;
import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.mappers.AdminMapper;
import org.soen387.datasource.mappers.InvoiceMapper;
import org.soen387.domain.Game;
import org.soen387.domain.Invoice;
import org.soen387.domain.User;

public class InvoiceTDG {

	private static InvoiceTDG instance = null;
	private InvoiceMapper invoiceMapper;
	
	private InvoiceTDG() {
		this.invoiceMapper = new InvoiceMapper();
	}
	
	public static InvoiceTDG getInstance() {
		if (InvoiceTDG.instance == null) {
			instance = new InvoiceTDG();
		}
		return instance;
	}
	/**
	 * Get all invoices for user
	 * @param id - User id
	 * @return User invoices
	 */
	public ArrayList<Invoice> getAllUserInvoices(int id){
		final String preparedQuery = "SELECT * FROM Invoice WHERE client_id = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(preparedQuery);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			return invoiceMapper.mapMultiple(resultSet);
		}
		catch (SQLException se) {
			System.out.println("Failed to execute getAllUserInvoices query: " + se.getMessage());
		}
		finally {
			DatabaseConnection.clearConnection();
		}
		return null;
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
		return result;
	}
	
	/**
	 * Fetch last inserted invoice ID
	 * @return invoice ID
	 * @throws SQLException
	 */
	public long fetchInvoiceId() throws SQLException{
		final String selectLastInsertId = "SELECT LAST_INSERT_ID()";
		
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(selectLastInsertId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getLong("last_insert_id()");
			}
		}
		catch (SQLException se) {
			System.out.println("Failed to execute insertInvoiceSql query: " + se.getMessage());
		} 
		finally {
			DatabaseConnection.clearConnection();
		}
		return 0;
	}
}
