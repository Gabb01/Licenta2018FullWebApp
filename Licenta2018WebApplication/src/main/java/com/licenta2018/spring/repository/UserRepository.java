package com.licenta2018.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.licenta2018.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);
}

