package org.soen387.services;

import java.sql.SQLException;

import org.soen387.business.ShoppingCart;
import org.soen387.datasource.gateways.InvoiceTDG;
import org.soen387.domain.User;

public class CheckoutService {
	public static CheckoutService instance = null;
	
	private CheckoutService() { }
	
	public int createInvoice(User user, ShoppingCart cart) {
		try {
			InvoiceTDG.getInstance().insertInvoice(user, cart);
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
	public static CheckoutService getInstance() {
		if (instance == null) {
			instance = new CheckoutService();
		}
		return instance;
	}

}
