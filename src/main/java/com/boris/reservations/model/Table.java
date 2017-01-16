package com.boris.reservations.model;

public class Table {
	
	private int number;
	private int capacity;
	
	public Table() {
	}

	public Table(int number, int capacity) {
		this.number = number;
		this.capacity = capacity;
	}

	public int getNumber() {
		return number;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + number;
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
		Table other = (Table) obj;
		if (capacity != other.capacity)
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	
}
