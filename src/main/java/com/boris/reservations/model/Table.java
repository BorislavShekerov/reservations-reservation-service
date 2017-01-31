package com.boris.reservations.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Table.
 */
@Entity(name="venue_table")
public class Table {
	
	/** The id. */
	@EmbeddedId
	@JsonIgnore
	private TablePrimaryKey tablePrimaryKey;
	/** The capacity. */
	@Basic
	private int capacity;
	
	/** The number. */
	@Transient
	private int number;
	
	/**
	 * Instantiates a new table.
	 */
	public Table() {
	}

	/**
	 * Instantiates a new table.
	 *
	 * @param tablePrimaryKey the table primary key
	 * @param capacity the capacity
	 */
	public Table(TablePrimaryKey tablePrimaryKey, int capacity) {
		this.tablePrimaryKey = tablePrimaryKey;
		this.capacity = capacity;
	}


	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		return this.tablePrimaryKey.getNumber();
	}
	
	/**
	 * Gets the table number sent with request.
	 *
	 * @return the table number sent with request
	 */
	@JsonIgnore
	public int getTableNumberSentWithRequest(){
		return this.number;
	}
	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	
	/**
	 * Sets the table primary key.
	 *
	 * @param tablePrimaryKey the new table primary key
	 */
	public void setTablePrimaryKey(TablePrimaryKey tablePrimaryKey) {
		this.tablePrimaryKey = tablePrimaryKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((tablePrimaryKey == null) ? 0 : tablePrimaryKey.hashCode());
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
		Table other = (Table) obj;
		if (capacity != other.capacity)
			return false;
		if (tablePrimaryKey == null) {
			if (other.tablePrimaryKey != null)
				return false;
		} else if (!tablePrimaryKey.equals(other.tablePrimaryKey))
			return false;
		return true;
	}


	/**
	 * The Class TablePrimaryKey.
	 */
	@Embeddable
	public static class TablePrimaryKey implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 4185058160908465923L;

		/** The number. */
		@Column(nullable=false)
	    private int number;

		/** The venue belonging to. */
		@ManyToOne
	    private Venue venueBelongingTo;
		
		/**
		 * Instantiates a new table primary key.
		 */
		public TablePrimaryKey() {
		}
		
		/**
		 * Instantiates a new table primary key.
		 *
		 * @param number the number
		 * @param venueBelongingTo the venue belonging to
		 */
		public TablePrimaryKey(int number, Venue venueBelongingTo) {
			this.number = number;
			this.venueBelongingTo = venueBelongingTo;
		}

		/**
		 * Gets the number.
		 *
		 * @return the number
		 */
		public int getNumber() {
			return number;
		}

		/**
		 * Sets the number.
		 *
		 * @param number the new number
		 */
		public void setNumber(int number) {
			this.number = number;
		}

		/**
		 * Gets the venue belonging to.
		 *
		 * @return the venue belonging to
		 */
		public Venue getVenueBelongingTo() {
			return venueBelongingTo;
		}

		/**
		 * Sets the venue belonging to.
		 *
		 * @param venueBelongingTo the new venue belonging to
		 */
		public void setVenueBelongingTo(Venue venueBelongingTo) {
			this.venueBelongingTo = venueBelongingTo;
		}
		
	}
}
