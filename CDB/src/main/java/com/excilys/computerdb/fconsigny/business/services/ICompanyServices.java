package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;

public interface ICompanyServices {

  public CompanyDto getUniqueCompany(final long id) throws ServiceException;
  
  public List<CompanyDto> getAllCompanies() throws ServiceException;
}
