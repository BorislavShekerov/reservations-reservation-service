package com.boris.reservations.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boris.reservations.model.Reservation;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, Long> {

	public List<Reservation> findReservationsByUserReserved_Email(String userEmail);

	public List<Reservation> getReservationsByReservationDateAndVenue_Id(LocalDate reservationDate, String id);

	public List<Reservation> getReservationsByVenue_IdAndReservationDateBetweenAndIsAnswered(String venueId, LocalDate monthStart,
			LocalDate monthEnd, boolean isAnswered);

	public List<Reservation> getReservationsByReservationDateAndIsAnsweredAndIsConfirmed(LocalDate dateToFetchReservationsFor, boolean b, boolean c);

}
