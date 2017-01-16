package com.boris.reservations.service;

import java.util.List;

import com.boris.reservations.model.Table;

public interface VenueService {
	
	public List<Table> getTablesForVenue(String venueId);
}
