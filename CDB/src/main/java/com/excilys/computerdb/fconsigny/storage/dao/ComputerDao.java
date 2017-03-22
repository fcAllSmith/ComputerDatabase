package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;

public interface ComputerDao {

  public Computer findById(Connection connection, final long id);

  public List<Computer> findAll(Connection connection);

  public List<Computer> findAllWithLimiter(Connection connection, final String name, final int limit, final int offset);

  public boolean addComputer(Connection connection, Computer computer);

  public boolean updateComputer(Connection connection, Computer computer);

  public boolean deleteComputer(Connection connection, final long id);
  
  public int getCount(Connection connection);
}
