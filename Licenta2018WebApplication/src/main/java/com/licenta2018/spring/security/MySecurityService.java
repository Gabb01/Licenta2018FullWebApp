package com.licenta2018.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.licenta2018.spring.model.CustomUserDetail;
import com.licenta2018.spring.model.Request;
import com.licenta2018.spring.repository.RequestRepository;
import com.licenta2018.spring.repository.UserRepository;

@Component("mySecurityService")
public class MySecurityService {
	
	@Autowired
	UserRepository users;
	
	@Autowired
	RequestRepository requests;
	
	public boolean canApproveBooking(long request_id, CustomUserDetail user){
		Request request = requests.findOne(request_id);
		return request != null && user != null && request.getUser().getId() == user.getUser().getId();
	}
}