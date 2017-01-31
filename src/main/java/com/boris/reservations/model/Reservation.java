package com.boris.reservations.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * The Class Reservation.
 */
@Entity
public class Reservation {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/** The reservation date. */
	@Basic
	private LocalDate reservationDate;
	
	/** The venue. */
	@ManyToOne(cascade={CascadeType.REFRESH})
	private Venue venue;
	
	/** The people attending. */
	@Basic
	private int peopleAttending;
	
	/** The table reserved. */
	@OneToOne(cascade={CascadeType.REFRESH})
	private Table tableReserved;
	
	/** The user reserved. */
	@ManyToOne(cascade={CascadeType.REFRESH})
	private User userReserved;
	
	/** The is active. */
	@Basic
	private boolean isActive;

	/**
	 * Gets the user reserved.
	 *
	 * @return the user reserved
	 */
	public User getUserReserved() {
		return userReserved;
	}

	/**
	 * Sets the user reserved.
	 *
	 * @param userReserved the new user reserved
	 */
	public void setUserReserved(User userReserved) {
		this.userReserved = userReserved;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	private void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the reservation date.
	 *
	 * @return the reservation date
	 */
	public LocalDate getReservationDate() {
		return reservationDate;
	}

	/**
	 * Sets the reservation date.
	 *
	 * @param reservationDate the new reservation date
	 */
	private void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	/**
	 * Gets the venue.
	 *
	 * @return the venue
	 */
	public Venue getVenue() {
		return venue;
	}

	/**
	 * Sets the venue.
	 *
	 * @param venue the new venue
	 */
	private void setVenue(Venue venue) {
		this.venue = venue;
	}

	/**
	 * Gets the people attending.
	 *
	 * @return the people attending
	 */
	public int getPeopleAttending() {
		return peopleAttending;
	}

	/**
	 * Sets the people attending.
	 *
	 * @param peopleAttending the new people attending
	 */
	private void setPeopleAttending(int peopleAttending) {
		this.peopleAttending = peopleAttending;
	}

	/**
	 * Gets the table reserved.
	 *
	 * @return the table reserved
	 */
	public Table getTableReserved() {
		return tableReserved;
	}

	/**
	 * Sets the table reserved.
	 *
	 * @param tableReserved the new table reserved
	 */
	public void setTableReserved(Table tableReserved) {
		this.tableReserved = tableReserved;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return isActive;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + peopleAttending;
		result = prime * result + ((reservationDate == null) ? 0 : reservationDate.hashCode());
		result = prime * result + ((tableReserved == null) ? 0 : tableReserved.hashCode());
		result = prime * result + ((userReserved == null) ? 0 : userReserved.hashCode());
		result = prime * result + ((venue == null) ? 0 : venue.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
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
		if (userReserved == null) {
			if (other.userReserved != null)
				return false;
		} else if (!userReserved.equals(other.userReserved))
			return false;
		if (venue == null) {
			if (other.venue != null)
				return false;
		} else if (!venue.equals(other.venue))
			return false;
		return true;
	}



	/**
	 * The Class ReservationBuilder.
	 */
	public static class ReservationBuilder {
		
		/** The reservation. */
		private Reservation reservation;

		/**
		 * Instantiates a new reservation builder.
		 */
		public ReservationBuilder() {
			this.reservation = new Reservation();
		}

		/**
		 * With reservation date.
		 *
		 * @param reservationDate the reservation date
		 * @return the reservation builder
		 */
		public ReservationBuilder withReservationDate(LocalDate reservationDate) {
			this.reservation.setReservationDate(reservationDate);
			return this;
		}

		/**
		 * With venue.
		 *
		 * @param venue the venue
		 * @return the reservation builder
		 */
		public ReservationBuilder withVenue(Venue venue) {
			this.reservation.setVenue(venue);
			return this;
		}

		/**
		 * With people attending.
		 *
		 * @param peopleAttending the people attending
		 * @return the reservation builder
		 */
		public ReservationBuilder withPeopleAttending(int peopleAttending) {
			this.reservation.setPeopleAttending(peopleAttending);
			return this;
		}

		/**
		 * With id.
		 *
		 * @param id the id
		 * @return the reservation builder
		 */
		public ReservationBuilder withId(long id) {
			this.reservation.setId(id);
			return this;
		}

		/**
		 * With user reserved email.
		 *
		 * @param userReserved the user reserved
		 * @return the reservation builder
		 */
		public ReservationBuilder withUserReserved(User userReserved) {
			this.reservation.setUserReserved(userReserved);
			return this;
		}

		/**
		 * With table reserved.
		 *
		 * @param tableReserved the table reserved
		 * @return the reservation builder
		 */
		public ReservationBuilder withTableReserved(Table tableReserved) {
			this.reservation.setTableReserved(tableReserved);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the reservation
		 */
		public Reservation build() {
			return this.reservation;
		}
	}
}
