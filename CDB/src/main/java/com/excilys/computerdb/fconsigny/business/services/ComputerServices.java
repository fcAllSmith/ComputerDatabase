package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.database.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComputerServices implements IComputerServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();

  @Autowired
  IMysqlDatasource datasource; 

  JdbcTemplate jdbcTemplate; 

  public ComputerServices(){

  }

  @Transactional(readOnly=true)
  public Computer getUniqueComputer(final long id) {
    Computer computer = computerDao.findById(jdbcTemplate,id);

    try {
      datasource.closeConnection(getSelfConnection().getDataSource().getConnection());
      return computer; 
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Transactional(readOnly = true)
  public List<Computer> getAllComputers() throws ServiceException {
    List<Computer> computerList = computerDao.findAll(getSelfConnection()); 

    try {
      datasource.closeConnection(getSelfConnection().getDataSource().getConnection());
      return computerList;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Transactional(readOnly = true)
  public List<Computer> getAllComputersWithLimiter (final int offset, final int limit, final String name) throws ServiceException {
    List<Computer> computerList = null;  
    computerList = computerDao.findAllWithLimiter(getSelfConnection(),name, limit, offset);

    try {
      datasource.closeConnection(getSelfConnection().getDataSource().getConnection());
    } catch (SQLException e) {
      throw new ServiceException (e.toString());
    }

    return computerList; 
  }

  /**
   * 
   * @param computerDto
   * @return true if successful.
   */
  @Transactional(readOnly = true)
  public boolean saveComputer(final Computer computer) throws ServiceException {
    return computerDao.addComputer(getSelfConnection(),computer);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
  public boolean editComptuter(final Computer computer) throws ServiceException {
    try {
      return computerDao.updateComputer(getSelfConnection(),computer);
    } finally {
      try {
        datasource.closeConnection(getSelfConnection().getDataSource().getConnection());
      } catch (SQLException e) {
        throw new ServiceException (e.toString());
      }
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = SQLException.class)
  public boolean deleteComputer(final long id) throws ServiceException{
    try {
      return computerDao.deleteComputer(getSelfConnection(),id);
    } finally {
      try {
        datasource.closeConnection(datasource.getConnection());
      } catch (SQLException | DatabaseException e) {
        throw new ServiceException (e.toString());
      }
    }
  }

  private JdbcTemplate getSelfConnection(){

    try {
      if(this.jdbcTemplate == null || jdbcTemplate.getDataSource().getConnection().isClosed()){
        try {
          this.jdbcTemplate = new JdbcTemplate(datasource.getDataSource());
        } catch (ClassNotFoundException e) {
          return null;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return jdbcTemplate;
  }

}
