package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;

public interface ComputerDao {

  public Computer findById(final long id);

  public List<Computer> findAll();

  public List<Computer> findAllWithLimiter(final String name, final int limit, final int offset);

  public boolean addComputer(Computer computer);

  public boolean updateComputer(Computer computer);

  public boolean deleteComputer(final long id);
}
