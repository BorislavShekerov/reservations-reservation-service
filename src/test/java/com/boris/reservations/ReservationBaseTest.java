package com.boris.reservations;

import java.time.LocalDate;

import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Reservation.ReservationBuilder;
import com.boris.reservations.model.Table.TablePrimaryKey;
import com.boris.reservations.model.Table;
import com.boris.reservations.model.User;
import com.boris.reservations.model.Venue;

public class ReservationBaseTest {
	
	protected static final LocalDate DUMMY_RESERVATION_DATE = LocalDate.now();

	protected static final String DUMMY_VENUE_ID = "dummyVenueId";
	
	protected static final Venue DUMMY_RESERVED_VENUE = new Venue(DUMMY_VENUE_ID, null, null, null);
	
	protected static final int DUMMY_RESERVATION_PEOPLE_ATTENDING = 10;

	protected static final long DUMMY_RESERVATION_ID = 1l;

	protected static final User DUMMY_USER = new User("dummyEmail", "dummyUserFirstname", "dummyUserLastname");

	protected static final User DUMMY_USER_2 = new User("dummyEmail2", "dummyUserFirstname2", "dummyUserLastname2");
	
	protected final TablePrimaryKey pk1 = new TablePrimaryKey(1, DUMMY_RESERVED_VENUE);

	protected final TablePrimaryKey pk2 = new TablePrimaryKey(2, DUMMY_RESERVED_VENUE);
	
	protected final Table venueTable_capacity2 = new Table(pk1, 2);
	
	protected final Table venueTable_capacity5 = new Table(pk2, 5);

	protected final Reservation DUMMY_RESERVATION = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
			.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(DUMMY_RESERVATION_DATE)
			.withVenue(DUMMY_RESERVED_VENUE).withTableReserved(venueTable_capacity2).withUserReserved(DUMMY_USER).build();

	protected final Reservation DUMMY_RESERVATION_2 = new ReservationBuilder().withId(DUMMY_RESERVATION_ID)
			.withPeopleAttending(DUMMY_RESERVATION_PEOPLE_ATTENDING).withReservationDate(DUMMY_RESERVATION_DATE)
			.withVenue(DUMMY_RESERVED_VENUE).withTableReserved(venueTable_capacity5).withUserReserved(DUMMY_USER_2).build();

}
