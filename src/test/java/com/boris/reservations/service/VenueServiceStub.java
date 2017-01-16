package com.boris.reservations.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boris.reservations.model.Table;

@Service
public class VenueServiceStub implements VenueService {

	public static final Table AVAILABLE_TABLE = new Table(1, 4);

	@Override
	public List<Table> getTablesForVenue(String venueId) {
		return Arrays.asList(AVAILABLE_TABLE);
	}

}
