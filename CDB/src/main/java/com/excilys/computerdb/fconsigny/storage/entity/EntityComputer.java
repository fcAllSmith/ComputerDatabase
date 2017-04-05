package com.excilys.computerdb.fconsigny.storage.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class EntityComputer {

  @Id @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;
  
  @Column(name = "introduced")
  private LocalDateTime introduced;
  
  @Column(name = "discontinued")
  private LocalDateTime discontinued;
  
  @Column(name = "company_id")
  private int companyId;
  
  public EntityComputer(){
    
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getIntroduced() {
    return introduced;
  }

  public void setIntroduced(LocalDateTime introduced) {
    this.introduced = introduced;
  }

  public LocalDateTime getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(LocalDateTime discontinued) {
    this.discontinued = discontinued;
  }

  public int getCompanyId() {
    return companyId;
  }

  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }
}
