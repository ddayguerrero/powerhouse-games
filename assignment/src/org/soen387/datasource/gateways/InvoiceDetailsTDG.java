package org.soen387.datasource.gateways;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.soen387.datasource.DatabaseConnection;
import org.soen387.datasource.mappers.InvoiceDetailsMapper;
import org.soen387.domain.CartItem;
import org.soen387.domain.Game;
import org.soen387.domain.InvoiceDetails;

public class InvoiceDetailsTDG {
	private static InvoiceDetailsTDG instance = null;
	private InvoiceDetailsMapper invoiceDetailsMapper;

	private InvoiceDetailsTDG() {
		this.invoiceDetailsMapper = new InvoiceDetailsMapper();
	}
	
	public static InvoiceDetailsTDG getInstance() {
		if (InvoiceDetailsTDG.instance == null) {
			instance = new InvoiceDetailsTDG();
		}
		return instance;
	}
	/**
	 * Get details for existing invoice
	 * @param invoiceId
	 * @return
	 */
	public ArrayList<InvoiceDetails> getInvoiceDetails(int invoiceId) {
//		final String getInvoiceDetailsSql = "SELECT INVOICE_ID, QUANTITY, invoice_details.GAME_ID, GAME_PRICE,"
//				+ "game.TITLE FROM Invoice_Details JOIN Game USING(GAME_ID) WHERE INVOICE_ID = ?";
		
		final String getInvoiceDetailsSql = "SELECT invoice_id, quantity, Invoice_Details.game_id, game_price"
				+ " FROM Invoice_Details LEFT JOIN Game ON Invoice_Details.game_id = Game.game_id WHERE invoice_id = ?;";
		try {
			PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(getInvoiceDetailsSql);
			ps.setInt(1, invoiceId);
			ResultSet rs = ps.executeQuery();
			return invoiceDetailsMapper.mapMultiple(rs);
		}
		catch (SQLException se) {
			System.out.println("Failed to execute getInvoiceDetails query: " + se.getMessage());
		}
		finally {
			DatabaseConnection.clearConnection();
		}
		return null;
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
