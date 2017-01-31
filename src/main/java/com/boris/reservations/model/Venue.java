package com.boris.reservations.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * The Class Venue.
 */
@Entity
public class Venue {
	
	@Id
	private String id;
	
	/** The name. */
	@Basic
	private String name;
	
	/** The main pic url. */
	@Basic
	private String mainPicUrl;
	
	/** The location. */
	@OneToOne(cascade=CascadeType.ALL)
	private Location location;
	
	/** The reservations. */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="venue")
	private List<Reservation> reservations = new ArrayList<>();
	/**
	 * Instantiates a new venue.
	 */
	public Venue() {
	}

	/**
	 * Instantiates a new venue.
	 *
	 * @param id the id
	 * @param name the name
	 * @param mainPicUrl the main pic url
	 * @param location the location
	 */
	public Venue(String id, String name, String mainPicUrl, Location location) {
		this.id = id;
		this.name = name;
		this.mainPicUrl = mainPicUrl;
		this.location = location;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the main pic url.
	 *
	 * @return the main pic url
	 */
	public String getMainPicUrl() {
		return mainPicUrl;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public void addReservation(Reservation reservation){
		this.reservations.add(reservation);
	}

}
