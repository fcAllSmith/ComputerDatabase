package com.excilys.computerdb.fconsigny.business.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.factory.CompanyFactory;
import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.exceptions.CompanyException;

public class CompanyServices {

  private CompanyDao cDao =  CompanyFactory.getCompanyDao();

  public Company getUniqueCompany(long id) throws CompanyException {
    try {
      return cDao.findById(id);
    } catch (SQLException error) {
    	error.printStackTrace();
        throw new CompanyException("Company not found");
    }
  }
	
  public List<Company> getAllCompanies() {
    try {
      return cDao.findAll();
    } catch (SQLException e) {
      e.printStackTrace();
    return Collections.emptyList();
    } 
  }
}
