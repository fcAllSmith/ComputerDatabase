package src.main.java.com.excilys.computerdb.fconsigny.service.factory;

import src.main.java.com.excilys.computerdb.fconsigny.persistence.dao.CompanyDao;
import src.main.java.com.excilys.computerdb.fconsigny.persistence.dao.CompanyDaoImpl;

public class CompanyFactory {
  
  public static CompanyDao getCompanyDao() {
    return new CompanyDaoImpl();
  }
}
