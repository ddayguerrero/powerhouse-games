package org.soen387.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	
	private int userid;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	private String credit_card_type;
	private String credit_card_number;
	private String credit_card_cvv;
	private String credit_card_expiry;
	private int failed_logins;
	private Timestamp lastLogin;
	private Timestamp passwordExpiry;
	private Boolean locked;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCredit_card_type() {
		return credit_card_type;
	}
	public void setCredit_card_type(String credit_card_type) {
		this.credit_card_type = credit_card_type;
	}
	public String getCredit_card_number() {
		return credit_card_number;
	}
	public void setCredit_card_number(String credit_card_number) {
		this.credit_card_number = credit_card_number;
	}
	public String getCredit_card_cvv() {
		return credit_card_cvv;
	}
	public void setCredit_card_cvv(String credit_card_cvv) {
		this.credit_card_cvv = credit_card_cvv;
	}
	public String getCredit_card_expiry() {
		return credit_card_expiry;
	}
	public void setCredit_card_expiry(String credit_card_expiry) {
		this.credit_card_expiry = credit_card_expiry;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public int getFailed_logins() {
		return failed_logins;
	}
	public void setFailed_logins(int failed_logins) {
		this.failed_logins = failed_logins;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Timestamp getPasswordExpiry() {
		return passwordExpiry;
	}
	public void setPasswordExpiry(Timestamp password_expiry) {
		this.passwordExpiry = password_expiry;
	}
	
	
}
