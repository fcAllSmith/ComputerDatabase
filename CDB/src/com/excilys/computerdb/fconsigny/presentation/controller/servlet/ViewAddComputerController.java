package com.excilys.computerdb.fconsigny.presentation.controller.servlet;

import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

public class ViewAddComputerController {
	
	public CompanyDto getCompanyDtoList(){
		return null;
	}
	
	public void createComputer(String name, String introduced, String discontinued,String computer){
		ComputerDto computerDto = new ComputerDto(); 
		computerDto.setInserted(introduced);
		computerDto.setDiscontinued(discontinued);
		//computerDto.setCompanyId(companyId);
	}
}
