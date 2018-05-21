package com.licenta2018.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.licenta2018.spring.model.CustomUserDetail;
import com.licenta2018.spring.repository.RequestRepository;
import com.licenta2018.spring.repository.UserRepository;

@Controller
public class ApplicationController {

	@Autowired
	RequestRepository requests;
	
	@Autowired
	UserRepository users;

	@RequestMapping(value="/")
	public String root(Model model) {
		model.addAttribute("requests", requests.findAll());
		return "landing-page";
	}
	
	@RequestMapping(value="/angular")
	public String angular() {		
		return "angular-interface";
	}
	
	@RequestMapping(value="/signedin")
	public String signedIn(Model model, Authentication authentication) {
		
		CustomUserDetail principal = (authentication != null) ? (CustomUserDetail) authentication.getPrincipal() : null;

		if (principal != null) {
			String a = ((SimpleGrantedAuthority) principal.getAuthorities().iterator().next()).getAuthority();
			if (a.equals(("ROLE_USER")))
				return "redirect:/users/me";
			else if (a.equals("ROLE_HOTEL_MANAGER"))
				return "redirect:/users/me";
		}
		
		return "/"; // fallback
	}
}
