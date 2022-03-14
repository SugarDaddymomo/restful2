package com.tothenew.sharda.UserStuff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Class representing as a real life User.")
public class User {
	
	@ApiModelProperty(notes = "The unique ID of an user")
	private Integer id;
	
	@ApiModelProperty(notes = "The username of an user")
	private String username;
	
	@ApiModelProperty(notes = "Full name of an user")
	private String fullName;
	
	public User(Integer id, String username, String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
	}
	
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, fullName=%s]", id, username, fullName);
	}
}