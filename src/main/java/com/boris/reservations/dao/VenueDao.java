package com.boris.reservations.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boris.reservations.model.Venue;

@Repository
public interface VenueDao extends CrudRepository<Venue, String> {

}
