package com.boris.reservations.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Reservation {

	@Id
	private String id;
	private LocalDate reservationDate;
	private Venue venue;
	private int peopleAttending;
	private Table tableReserved;
	@JsonIgnore
	private String userReservedEmail;

	public String getUserReservedEmail() {
		return userReservedEmail;
	}

	public void setUserReservedEmail(String userReservedEmail) {
		this.userReservedEmail = userReservedEmail;
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	private void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Venue getVenue() {
		return venue;
	}

	private void setVenue(Venue venue) {
		this.venue = venue;
	}

	public int getPeopleAttending() {
		return peopleAttending;
	}

	private void setPeopleAttending(int peopleAttending) {
		this.peopleAttending = peopleAttending;
	}

	public Table getTableReserved() {
		return tableReserved;
	}

	public void setTableReserved(Table tableReserved) {
		this.tableReserved = tableReserved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + peopleAttending;
		result = prime * result + ((reservationDate == null) ? 0 : reservationDate.hashCode());
		result = prime * result + ((tableReserved == null) ? 0 : tableReserved.hashCode());
		result = prime * result + ((userReservedEmail == null) ? 0 : userReservedEmail.hashCode());
		result = prime * result + ((venue == null) ? 0 : venue.hashCode());
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
		Reservation other = (Reservation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (peopleAttending != other.peopleAttending)
			return false;
		if (reservationDate == null) {
			if (other.reservationDate != null)
				return false;
		} else if (!reservationDate.equals(other.reservationDate))
			return false;
		if (tableReserved == null) {
			if (other.tableReserved != null)
				return false;
		} else if (!tableReserved.equals(other.tableReserved))
			return false;
		if (userReservedEmail == null) {
			if (other.userReservedEmail != null)
				return false;
		} else if (!userReservedEmail.equals(other.userReservedEmail))
			return false;
		if (venue == null) {
			if (other.venue != null)
				return false;
		} else if (!venue.equals(other.venue))
			return false;
		return true;
	}


	public static class ReservationBuilder {
		private Reservation reservation;

		public ReservationBuilder() {
			this.reservation = new Reservation();
		}

		public ReservationBuilder withReservationDate(LocalDate reservationDate) {
			this.reservation.setReservationDate(reservationDate);
			return this;
		}

		public ReservationBuilder withVenue(Venue venue) {
			this.reservation.setVenue(venue);
			return this;
		}

		public ReservationBuilder withPeopleAttending(int peopleAttending) {
			this.reservation.setPeopleAttending(peopleAttending);
			return this;
		}

		public ReservationBuilder withId(String id) {
			this.reservation.setId(id);
			return this;
		}

		public ReservationBuilder withUserReservedEmail(String userReservedEmail) {
			this.reservation.setUserReservedEmail(userReservedEmail);
			return this;
		}

		public ReservationBuilder withTableReserved(Table tableReserved) {
			this.reservation.setTableReserved(tableReserved);
			return this;
		}

		public Reservation build() {
			return this.reservation;
		}
	}
}
