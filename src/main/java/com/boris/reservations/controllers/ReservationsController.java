package com.boris.reservations.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;
import com.boris.reservations.service.ReservationService;

@RestController
public class ReservationsController {
	
	private static final String DUMMY_USER_EMAIL = "dummyEmail";
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/reservations/")
	public List<Reservation> getUserReservations(){
		return reservationService.getReservationsForUser(DUMMY_USER_EMAIL).get();
	}
	
	@GetMapping("/reservations/{venueId}/{reservationDate}/{peopleAttending}")
	public List<Table> getFreeTablesForVenue(@PathVariable String venueId, @PathVariable String reservationDate,@PathVariable int peopleAttending){
		return reservationService.getFreeTablesForVenue(venueId, LocalDate.parse(reservationDate), peopleAttending).orElse(new ArrayList<>());
	}
	
	@GetMapping("/reservations/{venueId}/{reservationDate}")
	public List<Reservation> getAllReservationsForVenue(@PathVariable String venueId, @PathVariable String reservationDate){
		return reservationService.getAllReservationsForVenue(venueId, LocalDate.parse(reservationDate)).get();
	}
	
	@PostMapping("/reservations/")
	public Reservation addReservation(@RequestBody Reservation reservationToAdd){
		reservationToAdd.setUserReservedEmail(DUMMY_USER_EMAIL);
		
		return reservationService.saveReservation(reservationToAdd).orElse(null);
	}
	
}
