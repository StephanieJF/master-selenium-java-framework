package org.selenium.pom.objects;

public class BillingAddress {
	
	private String firstName;
	private String lastName;
	private String country;
	private String addressLineOne;
	private String city;
	private String state;
	private String postCode;
	private String email;
	private String phone;
	private String company;
	
	public BillingAddress() {
		
	}
	
	public BillingAddress(String firstName, String lastName, String country, String addressLineOne, 
			String city, String state, String postCode, String email) {
		this.firstName = firstName; //sets the class-level variable with the parameter
		this.lastName = lastName;    
		this.country = country;
		this.addressLineOne = addressLineOne;
		this.city = city;    
		this.state = state;
		this.postCode = postCode;    
		this.email = email;         
	}
	
	public String getFirstName() {
		return firstName;
	}
	public BillingAddress setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	public BillingAddress setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public String getCountry() {
		return country;
	}

	public BillingAddress setCountry(String country) {
		this.country = country;
		return this;
	}
	public String getAddressLineOne() {
		return addressLineOne;
	}
	public BillingAddress setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
		return this;
	}
	public String getCity() {
		return city;
	}
	public BillingAddress setCity(String city) {
		this.city = city;
		return this;
	}
	public String getState() {
		return state;
	}
	public BillingAddress setState(String state) {
		this.state = state;
		return this;
	}
	public String getPostCode() {
		return postCode;
	}
	public BillingAddress setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public BillingAddress setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public BillingAddress setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public String getCompany() {
		return company;
	}

	public BillingAddress setCompany(String company) {
		this.company = company;
		return this;
	}

}
