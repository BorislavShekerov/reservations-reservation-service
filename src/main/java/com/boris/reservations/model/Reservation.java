package com.boris.reservations.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reservation {
	
	@Id
	private long id;
	private LocalDate reservationDate;
	private long venueId;
	private int peopleAttending;
	private Table tableReserved;
	private String userReservedEmail;

	public String getUserReservedEmail() {
		return userReservedEmail;
	}

	public void setUserReservedEmail(String userReservedEmail) {
		this.userReservedEmail = userReservedEmail;
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	private void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public long getVenueId() {
		return venueId;
	}

	private void setVenueId(long venueId) {
		this.venueId = venueId;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + peopleAttending;
		result = prime * result + ((reservationDate == null) ? 0 : reservationDate.hashCode());
		result = prime * result + ((userReservedEmail == null) ? 0 : userReservedEmail.hashCode());
		result = prime * result + (int) (venueId ^ (venueId >>> 32));
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
		if (id != other.id)
			return false;
		if (peopleAttending != other.peopleAttending)
			return false;
		if (reservationDate == null) {
			if (other.reservationDate != null)
				return false;
		} else if (!reservationDate.equals(other.reservationDate))
			return false;
		if (userReservedEmail == null) {
			if (other.userReservedEmail != null)
				return false;
		} else if (!userReservedEmail.equals(other.userReservedEmail))
			return false;
		if (venueId != other.venueId)
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

		public ReservationBuilder withVenueId(long venueId) {
			this.reservation.setVenueId(venueId);
			return this;
		}

		public ReservationBuilder withPeopleAttending(int peopleAttending) {
			this.reservation.setPeopleAttending(peopleAttending);
			return this;
		}

		public ReservationBuilder withId(long id) {
			this.reservation.setId(id);
			return this;
		}

		public ReservationBuilder withUserReservedEmail(String userReservedEmail) {
			this.reservation.setUserReservedEmail(userReservedEmail);
			return this;
		}
		
		public ReservationBuilder withTableReserved(Table tableReserved){
			this.reservation.setTableReserved(tableReserved);
			return this;
		}

		public Reservation build() {
			return this.reservation;
		}
	}
}
