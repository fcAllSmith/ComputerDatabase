package src.main.java.com.excilys.computerdb.fconsigny.core.model;

import java.time.LocalDateTime;

public class Computer {

  
  private  int id;
  private String name;
  private LocalDateTime introduced;
  private LocalDateTime discontinued;
  private Company company;
  
  public Computer () {
    
  }
  
  public Computer(final int id, final String name, final LocalDateTime introduced, final LocalDateTime discontinued, final int companyId, final String companyName) {
    this.id = id; 
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued; 
    this.company = new Company(companyId,companyName);
  }
  
  public Computer(final int id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setIntroduced(final LocalDateTime introduced) {
    this.introduced = introduced;
  }

  public void setDiscontinued(final LocalDateTime discontinued) {
    this.discontinued = discontinued;
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
