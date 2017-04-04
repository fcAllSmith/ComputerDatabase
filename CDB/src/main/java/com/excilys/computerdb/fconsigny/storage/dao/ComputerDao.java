package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;

@Repository
public interface ComputerDao {

  public Computer findById(JdbcTemplate jdbcTemplate, final long id);

  public List<Computer> findAll(JdbcTemplate jdbcTemplate);

  public List<Computer> findAllWithLimiter(JdbcTemplate jdbcTemplate, final String name, final int limit, final int offset);

  public boolean addComputer(JdbcTemplate jdbcTemplate, Computer computer);

  public boolean updateComputer(JdbcTemplate jdbcTemplate, Computer computer);

  public boolean deleteComputer(JdbcTemplate jdbcTemplate, final long id);
  
  public int getCount(JdbcTemplate jdbcTemplate, IPage page);
}
