package com.boris.reservations.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.boris.reservations.model.Reservation;
import com.boris.reservations.service.ReservationService;

public class ReservationServiceIntegrationTest extends ReservationsIntegrationBaseTest{
	
	@Autowired
	private ReservationService testObj;
	
//	@Test
//	public void saveReservation_2reservation_sameVenue(){
//		testObj.saveReservation(DUMMY_RESERVATION);
//		
//		Optional<List<Reservation>> reservations = testObj.getAllReservationsForVenue(DUMMY_VENUE_ID, DUMMY_RESERVATION_DATE);
//		
//		assertEquals("There should be 2 reservations for venue", 2, reservations.get().size());
//	}
	

}
