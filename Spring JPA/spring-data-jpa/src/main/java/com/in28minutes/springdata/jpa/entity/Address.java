package com.in28minutes.springdata.jpa.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

	private String line;
	private String line2;
	private String city;

	public Address() {

	}

	public Address(String line, String line2, String city) {
		super();
		this.line = line;
		this.line2 = line2;
		this.city = city;
	}

}
