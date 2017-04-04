package com.excilys.computerdb.fconsigny.business.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.database.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

@Service("pageService")
@Transactional
public class PageServices implements IPageServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();
  JdbcTemplate jdbcTemplate; 

  @Autowired
  IMysqlDatasource datasource;

  @Autowired
  public PageServices(){}

  @Override
  public List<Computer> getComputer(IPage page) throws ServiceException {
    List<Computer> computerList = null; 
    try {
      page.setMaxCount(computerDao.getCount(getSelfConnection()));
      computerList =  computerDao.findAllWithLimiter(getSelfConnection(), null, page.getLimite(), page.getOffset());
    } finally {
      try {
        datasource.closeConnection(datasource.getConnection());
      } catch (SQLException | DatabaseException e) {
        throw new ServiceException (e.toString());
      }
    }
    
    return computerList;
  }

  @Override
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException {
    List<Computer> computerList = null;
    try {
      computerList =  computerDao.findAllWithLimiter(getSelfConnection(), null, page.getLimite(),page.getOffset());
      datasource.getConnection().commit();
    } catch (SQLException | DatabaseException e) {
      throw new ServiceException("Service unreachable");
    }  finally {
      try {
        datasource.closeConnection(datasource.getConnection());
      } catch (SQLException | DatabaseException e) {
        throw new ServiceException (e.toString());
      }
    }
    
    return computerList; 
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return jdbcTemplate;
  }
}
