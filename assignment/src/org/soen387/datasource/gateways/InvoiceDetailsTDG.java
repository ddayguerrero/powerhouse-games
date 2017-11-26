package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.soen387.business.ShoppingCart;
import org.soen387.datasource.DatabaseConnection;
import org.soen387.domain.CartItem;
import org.soen387.domain.Game;
import org.soen387.domain.User;

public class InvoiceDetailsTDG {
	private static InvoiceDetailsTDG instance = null;

	private InvoiceDetailsTDG() {
		
	}
	
	public static InvoiceDetailsTDG getInstance() {
		if (InvoiceDetailsTDG.instance == null) {
			instance = new InvoiceDetailsTDG();
		}
		return instance;
	}
	
	/**
	 * Insert invoice details for existing invoice
	 * @param item - Cart Item
	 * @param invoiceId - Invoice id
	 * @return 1 - success or 0 - fail
	 */
	public int insertInvoice(long invoiceId, CartItem<?> item) throws SQLException {
		final String insertInvoiceDetailSql = "INSERT INTO INVOICE_DETAILS (INVOICE_ID, QUANTITY, GAME_ID, GAME_PRICE) VALUES (?, ?, ?, ?)";
		int result = 0;
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(insertInvoiceDetailSql);
			Game game = (Game)item.getItem();
			ps.setInt(1, (int)invoiceId);
			ps.setInt(2, item.getQuantity());
			ps.setInt(3, game.getGameid());
			ps.setBigDecimal(4, game.getPrice());
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
