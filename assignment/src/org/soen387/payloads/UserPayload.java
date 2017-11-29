package org.soen387.payloads;

import java.io.Serializable;

public class UserPayload implements Serializable{
	
	private static final long serialVersionUID = 5829570767860026447L;
	
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
	
	public UserPayload(String fname, String lname, String email, String password, String address1,
			String address2, String city, String province, String postal_code, String country) {
		super();
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
		this.password = password;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.postalCode = postal_code;
		this.country = country;
	}
	
	public UserPayload(String fname, String lname, String email, String address1, String address2, String city,
			String province, String postalCode, String country) {
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = country;
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
	
}
