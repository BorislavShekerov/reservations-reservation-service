package com.boris.reservations.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Location.
 */
@Entity
public class Location {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/** The longitude. */
	@Basic
	private double longitude;
	
	/** The latitude. */
	@Basic
	private double latitude;
		
	/**
	 * Instantiates a new location.
	 */
	public Location() {
	}
	
	/**
	 * Instantiates a new location.
	 *
	 * @param longitude the longitude
	 * @param latitude the latitude
	 */
	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

}
