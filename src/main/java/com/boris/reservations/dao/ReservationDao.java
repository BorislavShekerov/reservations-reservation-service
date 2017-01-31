package com.boris.reservations.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boris.reservations.model.Reservation;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, Long> {

	List<Reservation> findReservationsByUserReserved_Email(String userEmail);

	List<Reservation> getReservationsByReservationDateAndVenue_Id(LocalDate reservationDate, String id);

}
