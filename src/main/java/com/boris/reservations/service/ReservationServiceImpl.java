package com.boris.reservations.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.boris.reservations.dao.ReservationDao;
import com.boris.reservations.model.Reservation;
import com.boris.reservations.model.Table;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private static final Logger LOGGER = Logger.getLogger(ReservationServiceImpl.class);
	@Autowired
	ReservationDao reservationsDao;

	@Autowired
	VenueService venueService;
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() - 1, 1));
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boris.reservations.service.ReservationService#getReservationsForUser(
	 * java.lang.String)
	 */
	@Override
	public Optional<List<Reservation>> getReservationsForUser(String userEmail) {
		return Optional.ofNullable(reservationsDao.findReservationsByUserReservedEmail(userEmail));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.boris.reservations.service.ReservationService#getFreeTablesForVenue(
	 * long, java.time.LocalDate, int)
	 */
	@Override
	public Optional<List<Table>> getFreeTablesForVenue(String venueId, LocalDate reservationDate,
			final int peopleAttending) {
		Callable<List<Table>> tablesForVenueTask = () -> venueService.getTablesForVenue(venueId);
		
		Future<List<Table>> tablesForVenueFuture = executorService.submit(tablesForVenueTask);
		List<Table> freeTablesForVenue = null;
		
		List<Integer> reservedTableNumbers = reservationsDao.getReservationsByVenueIdAndReservationDate(venueId,reservationDate).stream()
				.map(reservation -> reservation.getTableReserved().getNumber()).collect(toList());
			
		try {
			freeTablesForVenue = tablesForVenueFuture.get().stream().filter(table -> !reservedTableNumbers.contains(table.getNumber()))
					.filter(table -> table.getCapacity() >= peopleAttending).collect(toList());
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.debug("Error while making async get tables request", e);
			e.printStackTrace();
		}
		
		
		return Optional.ofNullable(freeTablesForVenue);
	}
	
	@Async
	private Future<List<Table>> getTablesForVenue(String venueId) {
		return new AsyncResult<>(venueService.getTablesForVenue(venueId));
	}
	
	@Async
	private Future<List<Reservation>> getReservationsForVenue(String venueId, LocalDate reservationDate) {
		List<Reservation> reservationsForVenue = reservationsDao.getReservationsByVenueIdAndReservationDate(venueId,
				reservationDate);
		return new AsyncResult<>(reservationsForVenue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boris.reservations.service.ReservationService#
	 * getAllReservationsForVenue(long, java.time.LocalDate)
	 */
	@Override
	public Optional<List<Reservation>> getAllReservationsForVenue(String venueId, LocalDate reservationDate) {
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
