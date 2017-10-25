package org.soen387.domain;

public class User {
	
	private int user_id;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String postal_code;
	private String country;
	private String credit_card_type;
	private String credit_card_number;
	private String credit_card_cvv;
	private String credit_card_expiry;

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
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
	
}
