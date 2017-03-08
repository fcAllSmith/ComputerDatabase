package com.excilys.computerdb.fconsigny.presentation.dto;

import com.excilys.computerdb.fconsigny.storage.dao.ComputerDaoImpl;

public class ComputerDto {
	
	private long id = 0; 
	private String name = "default";
	private String introduced = "-"; 
	private String discontinued = "-"; 
	private long companyId;
	
	public ComputerDto(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		System.out.println ("in dto" + id);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString(){
		return " ID : " + this.id + " NAME : " + this.name + " INTRODUCED " + this.introduced + " DISCONTINUED : " + this.discontinued + " COMPANY :" + this.companyId; 
	}
	
}
