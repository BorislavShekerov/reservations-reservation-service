package com.boris.reservations.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boris.reservations.model.Reservation;
import com.boris.reservations.security.UserContext;
import com.boris.reservations.service.ReservationService;

@RestController
public class ReservationsManagerController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/reservations/confirmed/{reservationDate}")
	public List<Reservation> getCofirmedReservationsForDate(@AuthenticationPrincipal UserContext userContext, @PathVariable String reservationDate) {
		return reservationService.getAllConfirmedDailyReservations(LocalDate.parse(reservationDate));

	}

	@PutMapping("/reservations/")
	public void confirmReservations(@AuthenticationPrincipal UserContext userContext, @RequestBody List<Long> reservationToConfirm) {
		reservationService.confirmReservations(reservationToConfirm);
	}
}
