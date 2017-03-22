package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;
import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

@Service("pageService")
public class PageServices implements IPageServices {
  
  private final ComputerDao computerDao = ComputerFactory.getComputerDao();
      
  @Autowired
  IMysqlDatasource datasource;
  
  @Autowired
  public PageServices(){}

  @Override
  public List<Computer> getComputer(IPage page) throws ServiceException {
    try {
      page.setMaxCount(computerDao.getCount(datasource.getConnection()));
      return computerDao.findAllWithLimiter(datasource.getConnection(), null, page.getLimite(), page.getOffset());
    } catch (DatabaseException e) {
      throw new ServiceException("Service unreachable");
    }
  }

  @Override
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException {
    try {
      return computerDao.findAllWithLimiter(datasource.getConnection(), null, page.getLimite(),page.getOffset());
    } catch (DatabaseException e) {
      throw new ServiceException("Service unreachable");
    }
  }
}
