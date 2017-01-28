package com.boris.reservations;

import java.time.LocalDate;

import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Reservation.ReservationBuilder;
import com.boris.reservations.model.Table;
import com.boris.reservations.model.Venue;

public class ReservationBaseTest {
	
	protected static final LocalDate DUMMY_RESERVATION_DATE = LocalDate.now();

	protected static final String DUMMY_VENUE_ID = "12";
	
	protected static final Venue DUMMY_RESERVED_VENUE = new Venue(DUMMY_VENUE_ID, null, null, null);
	
	protected static final int DUMMY_RESERVATION_PEOPLE_ATTENDING = 10;

	protected static final String DUMMY_RESERVATION_ID = "1";

	protected static final String DUMMY_USER_EMAIL = "dummyEmail";

	protected final Table venueTable = new Table(1, 2);

	protected final Reservation DUMMY_RESERVATION = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
			.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(DUMMY_RESERVATION_DATE)
			.withVenue(DUMMY_RESERVED_VENUE).withTableReserved(venueTable).withUserReservedEmail(DUMMY_USER_EMAIL).build();

}
