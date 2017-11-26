package org.soen387.services;

import java.util.ArrayList;

import org.soen387.datasource.gateways.AdminTDG;
import org.soen387.datasource.gateways.InvoiceDetailsTDG;
import org.soen387.datasource.gateways.InvoiceTDG;
import org.soen387.domain.Admin;
import org.soen387.domain.Invoice;
import org.soen387.domain.InvoiceDetails;
import org.soen387.domain.User;

public class AdminService {

	private static AdminService instance = null;
	
	/**
	 * Authenticate admin
	 * @return Admin
	 */
	public Admin find(String email, String password) {
		Admin admin = AdminTDG.getInstance().getUserByEmail(email);
		if (admin != null) {
			if(admin.getPassword().equals(password)) {
				return admin;
			}
			else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Initiate call to retrieve all user invoices
	 * @param user
	 * @return Invoices
	 */
	public ArrayList<Invoice> getUserOrders(User user) {
		return InvoiceTDG.getInstance().getAllUserInvoices(user.getUserid());
	}
	
	/**
	 * Initiate call to retrieve invoice details
	 * @param invoiceId
	 * @return
	 */
	public ArrayList<InvoiceDetails> getOrderDetails(int invoiceId) {
		return InvoiceDetailsTDG.getInstance().getInvoiceDetails(invoiceId);
	}
	
	/**
	 * Singleton instance of admin service
	 * @return
	 */
	public static AdminService getInstance () {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
}
