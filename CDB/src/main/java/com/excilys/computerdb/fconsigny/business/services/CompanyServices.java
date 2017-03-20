package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.factory.CompanyFactory;
import com.excilys.computerdb.fconsigny.business.mapper.CompanyDtoMapper;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;

import java.util.List;

public class CompanyServices {

	private final CompanyDao cDao =  CompanyFactory.getCompanyDao();

	public CompanyDto getUniqueCompany(final long id) {
		return CompanyDtoMapper.transformToDto(cDao.findById(id));   
	}

	public List<CompanyDto> getAllCompanies() {
		return CompanyDtoMapper.transformListToDto(cDao.findAll());
	}
}
