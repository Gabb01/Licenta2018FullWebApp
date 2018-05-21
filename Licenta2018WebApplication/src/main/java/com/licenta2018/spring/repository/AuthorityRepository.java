package com.licenta2018.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.licenta2018.spring.model.Authority;


public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	
	Authority findByRole(String role);
}

