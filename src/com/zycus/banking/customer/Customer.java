package com.zycus.banking.customer;

import java.sql.Date;

import com.zycus.banking.util.Hash;

public class Customer {

	private String id;
	private String title;
	private String firstName;
	private String lastName;
	private Date dob;
	private boolean REG_STATUS = false;

	public Customer(String title, String firstName, String lastName, Date dob) {
		super();
		this.title = title;
		this.firstName = firstName.toUpperCase();
		this.lastName = lastName.toUpperCase();
		this.dob = dob;
		this.setId(Hash.generateHash(String.valueOf(System.currentTimeMillis())));
	}

	public Customer(String id, String title, String firstName, String lastName, Date dob) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.id = id;
	}

	public Customer(String id, String title, String firstName, String lastName, Date dob, boolean REG_STATUS) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.id = id;
		this.REG_STATUS = REG_STATUS;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase() + " "
				+ lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getRegStatus() {
		return this.REG_STATUS;
	}

}
