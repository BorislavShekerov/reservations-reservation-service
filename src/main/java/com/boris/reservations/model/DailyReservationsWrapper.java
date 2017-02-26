package com.boris.reservations.model;

import java.time.LocalDate;
import java.util.List;

public class DailyReservationsWrapper {
	private LocalDate reservationDate;
	private List<Reservation> acceptedReservation;
	private List<Reservation> newReservationRequests;
	
	public DailyReservationsWrapper(LocalDate reservationDate, List<Reservation> newReservationRequests,
			List<Reservation> acceptedReservation) {
		this.reservationDate = reservationDate;
		this.acceptedReservation = acceptedReservation;
		this.newReservationRequests = newReservationRequests;
	}
	
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public List<Reservation> getAcceptedReservation() {
		return acceptedReservation;
	}
	public List<Reservation> getNewReservationRequests() {
		return newReservationRequests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptedReservation == null) ? 0 : acceptedReservation.hashCode());
		result = prime * result + ((newReservationRequests == null) ? 0 : newReservationRequests.hashCode());
		result = prime * result + ((reservationDate == null) ? 0 : reservationDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyReservationsWrapper other = (DailyReservationsWrapper) obj;
		if (acceptedReservation == null) {
			if (other.acceptedReservation != null)
				return false;
		} else if (!acceptedReservation.equals(other.acceptedReservation))
			return false;
		if (newReservationRequests == null) {
			if (other.newReservationRequests != null)
				return false;
		} else if (!newReservationRequests.equals(other.newReservationRequests))
			return false;
		if (reservationDate == null) {
			if (other.reservationDate != null)
				return false;
		} else if (!reservationDate.equals(other.reservationDate))
			return false;
		return true;
	}

}
