package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Company;

public interface CompanyDao {

  public Company findById(Connection connection, final long id);

  public List<Company> findAll(Connection connection);

}
