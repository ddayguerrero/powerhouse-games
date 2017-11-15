package org.soen387.services;

import org.soen387.datasource.gateways.AdminTDG;
import org.soen387.domain.Admin;

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
