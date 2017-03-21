package com.excilys.computerdb.fconsigny.business.model;

public class Company {

  private long id;
  private String name;

  public Company(final long id) {
    this.id = id;
  }

  public long getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return null;
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
