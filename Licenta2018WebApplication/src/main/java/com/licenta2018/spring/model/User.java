package com.licenta2018.spring.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	private String email;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
	private Set<Request> request = new HashSet<Request>();
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
	private Set<Audience> audience = new HashSet<Audience>();
	
	@ManyToOne
	private Authority authority;
	
	public User() {}

	public User(String name, String username, String password, String email) {
		this.name = name;
		this.username = username;
		this.setPassword(password);
		this.setEmail(email);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Request> getRequest() {
		return request;
	}

	public void setRequest(Set<Request> request) {
		this.request = request;
	}

	public Set<Audience> getAudience() {
		return audience;
	}

	public void setAudience(Set<Audience> audience) {
		this.audience = audience;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
}
