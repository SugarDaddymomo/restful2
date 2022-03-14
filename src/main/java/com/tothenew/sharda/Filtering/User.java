package com.tothenew.sharda.Filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//using JsonFilter to apply DYNAMIC filter
@JsonFilter("SomeUserFilter")
public class User {

	private Integer id;
	private String username;
	
	private String password;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//for ignoring it when retreiving data while STATIC filtering
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	//setting the password while using POST in STATIC filtering
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
}