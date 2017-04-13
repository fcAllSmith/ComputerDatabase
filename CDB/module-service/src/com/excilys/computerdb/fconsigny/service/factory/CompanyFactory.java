package com.excilys.computerdb.fconsigny.service.factory;

import com.excilys.computerdb.fconsigny.persistence.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.persistence.dao.CompanyDaoImpl;

public class CompanyFactory {
  
  public static CompanyDao getCompanyDao() {
    return new CompanyDaoImpl();
  }
}
