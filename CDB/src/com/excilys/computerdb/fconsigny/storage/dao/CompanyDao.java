package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Company;

public interface CompanyDao {

  public Company findById(long id);

  public List<Company> findAll();
  
}
