package com.boris.reservations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boris.reservations.model.Table;

@Service
public class VenueServiceImpl implements VenueService {
	
	@Autowired
	VenueServiceFeignClient feignClient;
	
	@Override
	public List<Table> getTablesForVenue(String venueId) {
		return feignClient.getTablesForVenue(venueId);
	}

}
