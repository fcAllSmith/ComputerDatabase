package com.excilys.computerdb.fconsigny.model;

import java.time.LocalDateTime;

public class Computer {

  private long id; 
  private String name; 
  private LocalDateTime introduced; 
  private LocalDateTime discontinued; 
  private long companyId; 
	
  public Computer(long id) {
    this.id = id; 
  }
	
  public void setName(String name) {
    this.name = name;
  }

  public void setIntroduced(LocalDateTime ctime) {
    this.introduced = ctime;
  }

  public void setDiscontinued(LocalDateTime ptime) {
    this.discontinued = ptime;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDateTime getIntroduced() {
    return introduced;
  }

  public LocalDateTime getDiscontinued() {
    return discontinued;
  }

  public long getCompanyId() {
    return companyId;
  }

  @Override
  public String toString(){
    //return " ID : " + this.id + " Name : " + this.name + " introduced : " + this.getIntroduced().toString() + " discontinued" + this.discontinued.toString() + " company" + this.companyId;
    return "ID : " + " Name : " + this.name + " Company id : " + this.companyId;
  }

}
