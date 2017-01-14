package com.boris.reservations.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boris.reservations.dao.ReservationDao;
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationsDao;

	@Autowired
	VenueService venueService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boris.reservations.service.ReservationService#getReservationsForUser(
	 * java.lang.String)
	 */
	@Override
	public Optional<List<Reservation>> getReservationsForUser(String userEmail) {
		return Optional.ofNullable(reservationsDao.findReservationsByUserReservedEmail());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boris.reservations.service.ReservationService#getFreeTablesForVenue(
	 * long, java.time.LocalDate, int)
	 */
	@Override
	public Optional<List<Table>> getFreeTablesForVenue(long venueId, LocalDate reservationDate,
			final int peopleAttending) {
		List<Reservation> reservationsForVenue = reservationsDao.getReservationsByVenueIdAndReservationDate(venueId,
				reservationDate);
		List<Table> tablesForVenue = venueService.getTablesForVenue(venueId);

		List<Integer> reservedTableNumbers = reservationsForVenue.stream()
				.map(reservation -> reservation.getTableReserved().getNumber()).collect(toList());
		
		List<Table> freeTablesForVenue = tablesForVenue.stream().filter(table -> !reservedTableNumbers.contains(table.getNumber()))
				.filter(table -> table.getCapacity() >= peopleAttending).collect(toList());
		
		return Optional.ofNullable(freeTablesForVenue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boris.reservations.service.ReservationService#
	 * getAllReservationsForVenue(long, java.time.LocalDate)
	 */
	@Override
	public Optional<List<Reservation>> getAllReservationsForVenue(long venueId, LocalDate reservationDate) {
		return Optional
				.ofNullable(reservationsDao.getReservationsByVenueIdAndReservationDate(venueId, reservationDate));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boris.reservations.service.ReservationService#addReservation(com.
	 * boris.reservations.model.Reservation)
	 */
	@Override
	public Optional<Reservation> saveReservation(Reservation reservationToSave) {
		return Optional.of(reservationsDao.save(reservationToSave));
	}

}
