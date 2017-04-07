package src.main.java.com.excilys.computerdb.fconsigny.service.factory;

import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDaoImpl;

public class CompanyFactory {
  
  public static CompanyDao getCompanyDao() {
    return new CompanyDaoImpl();
  }
}
