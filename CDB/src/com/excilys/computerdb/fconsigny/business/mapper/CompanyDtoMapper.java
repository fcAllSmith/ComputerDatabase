package com.excilys.computerdb.fconsigny.business.mapper;

import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;

public class CompanyDtoMapper {

	public static CompanyDto transformToDto(Company company){
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(company.getId());
		if(company.getName() != null){
			companyDto.setName(company.getName());	
		}

		return companyDto;
	}
}
