package com.boris.reservations.controllers;

import static org.junit.Assert.assertEquals;
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
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;
import com.boris.reservations.security.UserContext;
import com.boris.reservations.service.ReservationServiceImpl;

public class ReservationsControllerTest extends ReservationBaseTest {

	@InjectMocks
	private ReservationsController testObj;

	@Mock
	private ReservationServiceImpl reservationService;

	@Before
	public void setup() {
		testObj = new ReservationsController();

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReservationsForUser() throws Exception {
		UserContext userContext = UserContext.create(DUMMY_USER.getEmail(), null, null, null);
		when(reservationService.getReservationsForUser(DUMMY_USER.getEmail()))
				.thenReturn(Optional.of(Arrays.asList(DUMMY_RESERVATION)));

		List<Reservation> result = this.testObj.getUserReservations(userContext);

		assertEquals("There should be a single reservation for the user", 1, result.size());
		assertEquals("Reservation ID should be the one mocked", DUMMY_RESERVATION_ID, result.get(0).getId());
	}

	@Test
	public void getFreeTablesForVenue() throws Exception {
		when(reservationService.getFreeTablesForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE,
				DUMMY_RESERVATION_PEOPLE_ATTENDING)).thenReturn(Optional.ofNullable(null));

		List<Table> freeTables = this.testObj.getFreeTablesForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE.toString(),
				DUMMY_RESERVATION_PEOPLE_ATTENDING);

		verify(reservationService).getFreeTablesForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE,
				DUMMY_RESERVATION_PEOPLE_ATTENDING);
		assertEquals("There should be a 0 available tables", 0, freeTables.size());
	}

	@Test
	public void getAllReservationsForVenue() throws Exception {
		when(reservationService.getAllReservationsForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE))
				.thenReturn(Optional.of(Arrays.asList(DUMMY_RESERVATION)));

		List<Reservation> result = this.testObj.getAllReservationsForVenue(DUMMY_VENUE_ID,
				DUMMY_RESERVATION_DATE.toString());

		verify(reservationService).getAllReservationsForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE);
		assertEquals("There should be a single reservation for venue", result.size(), 1);
		assertEquals("Reservation ID should be the one mocked", result.get(0).getId(), DUMMY_RESERVATION_ID);
	}

	@Test
	public void addReservation_successful() throws Exception {
		when(reservationService.saveReservation(DUMMY_RESERVATION)).thenReturn(DUMMY_RESERVATION);

		Reservation reservationAdded = this.testObj.addReservation(UserContext.create(null, null, null, null),
				DUMMY_RESERVATION);

		verify(reservationService).saveReservation(DUMMY_RESERVATION);

		assertTrue("Reservation should be successfully added", reservationAdded != null);

	}

	@Test
	public void addReservation_unsuccessful() throws Exception {
		when(reservationService.saveReservation(DUMMY_RESERVATION)).thenReturn(null);

		Reservation result = this.testObj.addReservation(UserContext.create(null, null, null, null), DUMMY_RESERVATION);

		verify(reservationService).saveReservation(DUMMY_RESERVATION);

		assertEquals("Reservation should be unsuccessful", null, result);
	}

}
