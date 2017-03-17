package com.excilys.computerdb.fconsigny.business.model;

public class Company {

  private long id; 
  private String name; 
	
  public Company(final long id) {
	  this.id = id;
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
