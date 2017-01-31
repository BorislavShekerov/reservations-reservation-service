package com.boris.reservations.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.boris.reservations.model.Table;
import com.boris.reservations.model.Table.TablePrimaryKey;

public interface TableDao extends CrudRepository<Table, TablePrimaryKey> {
	
	public List<Table> getTableByTablePrimaryKey_venueBelongingTo_Id(String venueId);
}
