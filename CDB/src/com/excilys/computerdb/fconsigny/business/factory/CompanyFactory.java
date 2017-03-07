package com.excilys.computerdb.fconsigny.business.factory;

import java.sql.SQLException;

import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDaoImpl;

public class CompanyFactory {

public CompanyFactory(){
	super();
}
  public static CompanyDao getCompanyDao() throws SQLException{
    return new CompanyDaoImpl(ServletFactory.getSession());
  }
}
