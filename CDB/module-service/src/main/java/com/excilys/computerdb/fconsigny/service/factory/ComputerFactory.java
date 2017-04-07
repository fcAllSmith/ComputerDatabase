package src.main.java.com.excilys.computerdb.fconsigny.service.factory;

import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDaoImpl;

public class ComputerFactory {

  public static ComputerDao getComputerDao() {
    return new ComputerDaoImpl();
  }
}
