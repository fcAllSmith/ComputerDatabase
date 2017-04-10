package com.excilys.computerdb.fconsigny.service.services;

import java.util.List;

import com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import com.excilys.computerdb.fconsigny.core.model.Company;

public interface ICompanyServices {

  public Company getUniqueCompany(final long id) throws ServiceException;
  
  public List<Company> getAllCompanies() throws ServiceException;
}
