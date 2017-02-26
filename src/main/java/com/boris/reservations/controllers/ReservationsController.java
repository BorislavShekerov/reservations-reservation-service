package com.boris.reservations.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boris.reservations.model.DailyReservationsWrapper;
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;
import com.boris.reservations.model.Table.TablePrimaryKey;
import com.boris.reservations.model.User;
import com.boris.reservations.security.UserContext;
import com.boris.reservations.service.ReservationService;

@RestController
public class ReservationsController {

	@Autowired
	ReservationService reservationService;

	@GetMapping("/reservations/")
	public List<Reservation> getUserReservations(@AuthenticationPrincipal UserContext user) {
		return reservationService.getReservationsForUser(user.getEmail()).get();
	}

	@GetMapping("/reservations/{venueId}/{reservationDate}/{peopleAttending}")
	public List<Table> getFreeTablesForVenue(@PathVariable String venueId, @PathVariable String reservationDate,
			@PathVariable int peopleAttending) {
		return reservationService.getFreeTablesForVenue(venueId, LocalDate.parse(reservationDate), peopleAttending)
				.orElse(new ArrayList<>());
	}

	@GetMapping("/reservations/{venueId}/{reservationDate}")
	public List<Reservation> getAllReservationsForVenue(@PathVariable String venueId,
			@PathVariable String reservationDate) {
		return reservationService.getAllReservationsForVenue(venueId, LocalDate.parse(reservationDate)).get();
	}

	@GetMapping("/reservations/new/{reservationDateFrom}")
	public List<DailyReservationsWrapper> getAllNewMonthlyReservationsForVenue(
			@AuthenticationPrincipal UserContext userContext,
			@PathVariable String reservationDateFrom) {
		Object venueId = userContext.getExtendedAttributes().get("venueId");
		if(venueId == null) return null;
		
		return reservationService.getAllNewMonthlyReservationsForVenue(venueId.toString(), LocalDate.parse(reservationDateFrom));
	}

	@PostMapping("/reservations/")
	public Reservation addReservation(@AuthenticationPrincipal UserContext userContext,
			@RequestBody Reservation reservationToAdd) {
		User userIssuingReservation = new User(userContext.getEmail(), userContext.getFirstName(),
				userContext.getLastName());
		Table tableReserved = reservationToAdd.getTableReserved();

		tableReserved.setTablePrimaryKey(
				new TablePrimaryKey(tableReserved.getTableNumberSentWithRequest(), reservationToAdd.getVenue()));
		reservationToAdd.setUserReserved(userIssuingReservation);
		reservationToAdd.setTableReserved(tableReserved);
		
		reservationToAdd.setConfirmed(false);
		reservationToAdd.setAnswered(false);

		return reservationService.saveReservation(reservationToAdd);
	}
	
	
	
	

}
