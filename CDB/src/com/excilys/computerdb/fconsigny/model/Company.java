package com.excilys.computerdb.fconsigny.model;

public class Company {

	private long id; 
	private String name; 
	
	public Company(long id) {
		// TODO Auto-generated constructor stub
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name; 
	}
}
