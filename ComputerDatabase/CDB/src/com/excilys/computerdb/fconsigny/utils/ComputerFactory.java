package com.excilys.computerdb.fconsigny.utils;

import com.excilys.computerdb.fconsigny.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.dao.ComputerDaoImpl;

public class ComputerFactory {

  public static ComputerDao getComputerDao(){
    return new ComputerDaoImpl();
  } 
}
