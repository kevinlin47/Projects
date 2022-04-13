package com.kevin.rest.webservices.restfulwebservices.versioning;

public class PersonV1 {
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PersonV1(String name) {
		super();
		this.name = name;
	}
	
	public PersonV1() {
		super();
	}
}
