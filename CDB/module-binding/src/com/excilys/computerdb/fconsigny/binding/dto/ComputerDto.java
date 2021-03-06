package com.excilys.computerdb.fconsigny.binding.dto;

public class ComputerDto {

  private long id = 0;
  private String name = "default";
  private String introduced = "-";
  private String discontinued = "-";
  private long companyId;
  private String companyName = "-";

  public ComputerDto() {

  }

  public long getId() {
    return id;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(final String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(final String discontinued) {
    this.discontinued = discontinued;
  }

  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(final long companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return " ID : " + this.id + " NAME : " + this.name + " INTRODUCED " + this.introduced + " DISCONTINUED : "
        + this.discontinued + " COMPANY :" + this.companyName;
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
