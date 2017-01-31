package com.boris.reservations.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boris.reservations.model.Table;
import com.boris.reservations.model.Venue;
import com.boris.reservations.model.Table.TablePrimaryKey;

@Service
public class VenueServiceStub implements VenueService {
	
	private static final String DUMMY_VENUE_ID = "dummyVenueId";
	
	private static final Venue DUMMY_RESERVED_VENUE = new Venue(DUMMY_VENUE_ID, null, null, null);
	
	private static final TablePrimaryKey pk = new TablePrimaryKey(1, DUMMY_RESERVED_VENUE);
	
	public static final Table AVAILABLE_TABLE = new Table(pk, 4);

	@Override
	public List<Table> getTablesForVenue(String venueId) {
		return Arrays.asList(AVAILABLE_TABLE);
	}

}
