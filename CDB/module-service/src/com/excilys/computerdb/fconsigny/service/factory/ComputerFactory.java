package com.excilys.computerdb.fconsigny.service.factory;

import com.excilys.computerdb.fconsigny.persistence.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.persistence.dao.ComputerDaoImpl;

public class ComputerFactory {

  public static ComputerDao getComputerDao() {
    return new ComputerDaoImpl();
  }
}
