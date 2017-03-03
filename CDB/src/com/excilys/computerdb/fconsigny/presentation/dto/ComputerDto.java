package com.excilys.computerdb.fconsigny.presentation.dto;

public class ComputerDto {
	
	private long id; 
	private String name = "";
	private String inserted = ""; 
	private String discontinued = ""; 
	private long companyId;
	
	public ComputerDto(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
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
		return " ID : " + this.id + " NAME : " + this.name + " INTRODUCED " + this.inserted + " DISCONTINUED : " + this.discontinued + " COMPANY :" + this.companyId; 
	}
	
}
