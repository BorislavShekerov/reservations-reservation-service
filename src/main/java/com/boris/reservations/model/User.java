package com.boris.reservations.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * The Class User.
 */
@Entity
public class User {

	/** The email. */
	@Id
	private String email;
	
	/** The first name. */
	@Basic
	private String firstName;
	
	/** The last name. */
	@Basic
	private String lastName;
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param email the email
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public User(String email, String firstName, String lastName) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/** The reservations. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userReserved")
	private List<Reservation> reservations;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

}
