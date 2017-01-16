package com.boris.reservations.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boris.reservations.model.Table;

@FeignClient("venue-service")
public interface VenueServiceFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/venues/{venueId}/tables")
	List<Table> getTablesForVenue(@PathVariable("venueId") String venueId);

}
