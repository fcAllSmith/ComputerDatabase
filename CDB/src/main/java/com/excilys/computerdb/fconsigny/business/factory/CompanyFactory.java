package com.excilys.computerdb.fconsigny.business.factory;

import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDaoImpl;

public class CompanyFactory {

  public static CompanyDao getCompanyDao() {
    return new CompanyDaoImpl();
  }
}
