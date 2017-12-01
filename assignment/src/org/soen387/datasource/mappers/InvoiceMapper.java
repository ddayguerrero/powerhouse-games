package org.soen387.datasource.mappers;

import java.sql.SQLException;

import org.soen387.business.ShoppingCart;
import org.soen387.datasource.gateways.InvoiceDetailsTDG;
import org.soen387.datasource.gateways.InvoiceTDG;
import org.soen387.domain.CartItem;
import org.soen387.domain.User;

public class InvoiceMapper {
	public static InvoiceMapper instance = null;
	
	private InvoiceMapper() { }
	
	public int createInvoice(User user, ShoppingCart cart) {
		try {
			InvoiceTDG.getInstance().insertInvoice(user, cart);
			long invoiceId = InvoiceTDG.getInstance().fetchInvoiceId();
			System.out.println("Adding details to invoice with ID: " + invoiceId);
			if (cart.isHasMemberPricing()) {
				for (CartItem<?> item: cart.getItems()) {
					InvoiceDetailsTDG.getInstance().insertInvoice(invoiceId, item);
				}
			} else {
				for (CartItem<?> item: cart.getItems()) {
					InvoiceDetailsTDG.getInstance().insertDiscountedInvoice(invoiceId, item);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Singleton instance of checkout service
	 * @return CheckoutService
	 */
	public static InvoiceMapper getInstance() {
		if (instance == null) {
			instance = new InvoiceMapper();
		}
		return instance;
	}

}
