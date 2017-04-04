package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.datasource.JdbcDataSource;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServices implements IComputerServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();

  @Autowired
  JdbcDataSource dataSource;

  private JdbcTemplate jdbc;

  public ComputerServices() {
   
  }

  @Transactional(readOnly=true)
  public Computer getUniqueComputer(final long id) {
    setDataSource(dataSource.getDataSource());
    return computerDao.findById(jdbc,id);
  }

  @Transactional(readOnly = true)
  public List<Computer> getAllComputers() throws ServiceException {
    setDataSource(dataSource.getDataSource());
    return computerDao.findAll(jdbc); 
  }

  @Transactional(readOnly = true)
  public List<Computer> getAllComputersWithLimiter (final int offset, final int limit, final String name) throws ServiceException {
    setDataSource(dataSource.getDataSource());
    return computerDao.findAllWithLimiter(jdbc,name, limit, offset);
  }

  /**
   * 
   * @param computerDto
   * @return true if successful.
   */
  //@Transactional(readOnly = true)
  public boolean saveComputer(final Computer computer) throws ServiceException {
    setDataSource(dataSource.getDataSource());
    return computerDao.addComputer(jdbc,computer);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
  public boolean editComptuter(final Computer computer) throws ServiceException {
    setDataSource(dataSource.getDataSource());
    return computerDao.updateComputer(jdbc,computer);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
  public boolean deleteComputer(final long id) throws ServiceException{
    setDataSource(dataSource.getDataSource());
    return computerDao.deleteComputer(jdbc, id);
  }

  public void setDataSource(DataSource dataSource) {
    this.jdbc = new JdbcTemplate(dataSource);
  }
}
