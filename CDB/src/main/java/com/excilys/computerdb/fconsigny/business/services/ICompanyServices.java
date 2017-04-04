package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.model.Company;

public interface ICompanyServices {

  public Company getUniqueCompany(final long id) throws ServiceException;
  
  public List<Company> getAllCompanies() throws ServiceException;
}
