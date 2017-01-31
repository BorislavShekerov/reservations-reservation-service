package com.boris.reservations.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.boris.reservations.ReservationBaseTest;
import com.boris.reservations.dao.ReservationDao;
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;

public class ReservationServiceImplTest extends ReservationBaseTest {

	@InjectMocks
	ReservationService testObj;

	@Mock
	ReservationDao reservationDaoMock;

	@Mock
	VenueService venueService;

	@Before
	public void setUp() {
		testObj = new ReservationServiceImpl();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReservationsForUser_reservationsExist() {
		when(reservationDaoMock.findReservationsByUserReserved_Email(DUMMY_USER.getEmail()))
				.thenReturn(Arrays.asList(DUMMY_RESERVATION));

		Optional<List<Reservation>> reservations = testObj.getReservationsForUser(DUMMY_USER.getEmail());

		assertTrue("There should be a reservation returned", reservations.isPresent());
		assertEquals("Reservation should be the one mocked", DUMMY_RESERVATION, reservations.get().get(0));
	}

	@Test
	public void getReservationsForUser_noReservations() {
		when(reservationDaoMock.findReservationsByUserReserved_Email(DUMMY_USER.getEmail())).thenReturn(null);

		Optional<List<Reservation>> reservations = testObj.getReservationsForUser(DUMMY_USER.getEmail());

		assertFalse("There should be a reservation returned", reservations.isPresent());
	}

	@Test
	public void getFreeTablesForVenue_noFreeTables() {
		when(reservationDaoMock.getReservationsByReservationDateAndVenue_Id(DUMMY_RESERVATION_DATE, DUMMY_VENUE_ID))
				.thenReturn(Arrays.asList(DUMMY_RESERVATION));

		when(venueService.getTablesForVenue(DUMMY_VENUE_ID)).thenReturn(Arrays.asList(venueTable_capacity2));

		Optional<List<Table>> freeTables = testObj.getFreeTablesForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE,
				DUMMY_RESERVATION_PEOPLE_ATTENDING);

		verify(reservationDaoMock).getReservationsByReservationDateAndVenue_Id(DUMMY_RESERVATION_DATE, DUMMY_VENUE_ID);
		verify(venueService).getTablesForVenue(DUMMY_VENUE_ID);
		assertEquals("There should be 0 free tables", 0, freeTables.get().size());
	}

	@Test
	public void getFreeTablesForVenue_freeTablesExist() {
		when(reservationDaoMock.getReservationsByReservationDateAndVenue_Id(DUMMY_RESERVATION_DATE, DUMMY_VENUE_ID))
				.thenReturn(Arrays.asList());

		when(venueService.getTablesForVenue(DUMMY_VENUE_ID)).thenReturn(Arrays.asList(venueTable_capacity2));

		int peopleAttendingMatchingTableSize = 2;
		Optional<List<Table>> freeTables = testObj.getFreeTablesForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE,
				peopleAttendingMatchingTableSize);

		verify(reservationDaoMock).getReservationsByReservationDateAndVenue_Id(DUMMY_RESERVATION_DATE, DUMMY_VENUE_ID);
		verify(venueService).getTablesForVenue(DUMMY_VENUE_ID);
		assertTrue("There should be no freeTables", freeTables.isPresent());
		assertEquals("There should be 1 free table", 1, freeTables.get().size());
	}

	@Test
	public void getAllReservationsForVenue() {
		when(reservationDaoMock.getReservationsByReservationDateAndVenue_Id(DUMMY_RESERVATION_DATE, DUMMY_VENUE_ID))
				.thenReturn(Arrays.asList(DUMMY_RESERVATION));

		Optional<List<Reservation>> reservations = testObj.getAllReservationsForVenue(DUMMY_VENUE_ID,
				DUMMY_RESERVATION_DATE);

		assertTrue("There should be a reservation returned", reservations.isPresent());
		assertEquals("Reservation should be the one mocked", DUMMY_RESERVATION, reservations.get().get(0));
	}

	@Test
	public void addReservation() {
		when(reservationDaoMock.save(DUMMY_RESERVATION)).thenReturn(DUMMY_RESERVATION);
		Reservation savedReservation = testObj.saveReservation(DUMMY_RESERVATION);

		assertEquals("Reservation should be the one mocked", DUMMY_RESERVATION, savedReservation);
	}
}
