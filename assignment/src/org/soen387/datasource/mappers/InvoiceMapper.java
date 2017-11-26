package org.soen387.datasource.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.soen387.domain.Invoice;

public class InvoiceMapper implements DataMapper<Invoice> {

	@Override
	public Invoice mapRow(ResultSet set) {
		ArrayList<Invoice> invoices = mapMultiple(set);
		if (invoices.isEmpty()) {
			return null;
		}
		return invoices.get(0);
	}

	@Override
	public ArrayList<Invoice> mapMultiple(ResultSet set) {
		final ArrayList<Invoice> invoices = new ArrayList<Invoice>();

		if (set != null) {
			try {
				while (set.next()) {
					final Invoice invoice = new Invoice();
					invoice.setId(set.getInt("invoice_id"));
					invoice.setDate(set.getTimestamp("sale_date"));
					invoice.setId(set.getInt("client_id"));
					invoice.setSubTotal(set.getBigDecimal("net_price"));
					invoice.setTax(set.getBigDecimal("tax"));
					invoice.setTotal(set.getBigDecimal("total"));
					invoices.add(invoice);
				}
			} catch (SQLException se) {
				System.out.println("Error in mapping multiple invoices: " + se.getMessage());
			}
		}
		return invoices;
	}

}
