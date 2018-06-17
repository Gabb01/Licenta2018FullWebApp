package com.licenta2018.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.licenta2018.spring.model.CustomUserDetail;
import com.licenta2018.spring.repository.RequestRepository;
import com.licenta2018.spring.repository.UserRepository;

@Component("mySecurityService")
public class MySecurityService {
	
	@Autowired
	UserRepository users;
	
	@Autowired
	RequestRepository requests;
	
	public boolean canApproveConstructionRequests(CustomUserDetail user){
		return user != null && user.getUser().getAuthority().getRole().equals("ROLE_CONSTRUCTION_AUTHORIZE");
	}
}