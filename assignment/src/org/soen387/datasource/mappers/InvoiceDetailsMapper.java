package org.soen387.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.soen387.domain.InvoiceDetails;

public class InvoiceDetailsMapper implements DataMapper<InvoiceDetails>{

	@Override
	public InvoiceDetails mapRow(ResultSet set) {
		ArrayList<InvoiceDetails> details = mapMultiple(set);
		if (details.isEmpty()) {
			return null;
		}
		return details.get(0);
	}

	@Override
	public ArrayList<InvoiceDetails> mapMultiple(ResultSet set) {
		final ArrayList<InvoiceDetails> details = new ArrayList<InvoiceDetails>();

		if (set != null) {
			try {
				while (set.next()) {
					final InvoiceDetails detail = new InvoiceDetails();
					detail.setInvoiceId(set.getInt("invoice_id"));
					detail.setQuantity(set.getInt("quantity"));
					detail.setGameId(set.getInt("game_id"));
					detail.setGamePrice(set.getBigDecimal("game_price"));
					details.add(detail);
				}
			} catch (SQLException se) {
				System.out.println("Error in mapping multiple invoices details: " + se.getMessage());
			}
		}
		return details;
	}

}
