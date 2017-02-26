package com.boris.reservations.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.boris.reservations.model.DailyReservationsWrapper;
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;

/**
 * @author sheke
 *
 */
public interface ReservationService {

	Optional<List<Reservation>> getReservationsForUser(String userEmail);

	Optional<List<Table>> getFreeTablesForVenue(String venueId, LocalDate reservationDate, int peopleAttending);

	Optional<List<Reservation>> getAllReservationsForVenue(String venueId, LocalDate reservationDate);

	Reservation saveReservation(Reservation reservationToAdd);

	List<DailyReservationsWrapper> getAllNewMonthlyReservationsForVenue(String venueId, LocalDate parse);

	void confirmReservations(List<Long> reservationToConfirm);

	List<Reservation> getAllConfirmedDailyReservations(LocalDate parse);

}