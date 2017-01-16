package com.boris.reservations.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boris.reservations.model.Reservation;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, String> {

	List<Reservation> findReservationsByUserReservedEmail(String userEmail);

	List<Reservation> getReservationsByVenueIdAndReservationDate(String venueId, LocalDate dummyReservationDate);

}
