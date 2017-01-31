package com.boris.reservations.dao;

import org.springframework.data.repository.CrudRepository;

import com.boris.reservations.model.User;

public interface UserDao extends CrudRepository<User, String> {

}
