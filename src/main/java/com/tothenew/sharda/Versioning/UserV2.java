package com.tothenew.sharda.Versioning;

import java.time.LocalDate;

public class UserV2 {
	
	private Name name;
	private LocalDate date;
	
	public UserV2() {
		
	}
	
	public UserV2(Name name, LocalDate date) {
		super();
		this.name = name;
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}	
}