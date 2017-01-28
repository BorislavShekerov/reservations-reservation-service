package com.boris.reservations.model;

public class Venue {
	private String id;
	private String name;
	private String mainPicUrl;
	private Location location;

	public Venue() {
	}

	public Venue(String id, String name, String mainPicUrl, Location location) {
		this.id = id;
		this.name = name;
		this.mainPicUrl = mainPicUrl;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getMainPicUrl() {
		return mainPicUrl;
	}

	public Location getLocation() {
		return location;
	}

	public String getId() {
		return id;
	}

}
