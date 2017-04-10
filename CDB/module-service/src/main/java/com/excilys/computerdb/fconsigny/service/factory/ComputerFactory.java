package src.main.java.com.excilys.computerdb.fconsigny.service.factory;

import src.main.java.com.excilys.computerdb.fconsigny.persistence.dao.ComputerDao;
import src.main.java.com.excilys.computerdb.fconsigny.persistence.dao.ComputerDaoImpl;

public class ComputerFactory {

  public static ComputerDao getComputerDao() {
    return new ComputerDaoImpl();
  }
}
