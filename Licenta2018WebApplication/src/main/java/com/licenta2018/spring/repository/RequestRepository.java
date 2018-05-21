package com.licenta2018.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.licenta2018.spring.model.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {
	
	
}

