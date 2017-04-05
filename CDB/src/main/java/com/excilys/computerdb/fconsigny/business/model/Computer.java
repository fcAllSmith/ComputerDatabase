package com.excilys.computerdb.fconsigny.business.model;

import java.time.LocalDateTime;

public class Computer {

  
  private  int id;
  private String name;
  private LocalDateTime introduced;
  private LocalDateTime discontinued;
  private Company company;
  
  public Computer () {
    
  }

  public Computer(final int id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setIntroduced(final LocalDateTime ctime) {
    this.introduced = ctime;
  }

  public void setDiscontinued(final LocalDateTime ptime) {
    this.discontinued = ptime;
  }

  public void setCompany(final Company company) {
    this.company = company;
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

  public Company getCompany() {
    return company;
  }

  @Override
  public String toString() {
    // return " ID : " + this.id + " Name : " + this.name + " introduced : " +
    // this.getIntroduced().toString() + " discontinued" +
    // this.discontinued.toString() + " company" + this.companyId;
    return "ID : " + " Name : " + this.name + " Company Name : " + this.company.getName();
  }

  @Override
  public boolean equals(Object obj) {
    // TODO redefine method
    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
