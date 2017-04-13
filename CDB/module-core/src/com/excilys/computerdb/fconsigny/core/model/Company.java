package com.excilys.computerdb.fconsigny.core.model;

public class Company {

  private long id;
  private String name;

  public Company(final long id) {
    this.id = id;
  }

  public Company(long id, String name) {
    this.id = id ; 
    this.name = name; 
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
