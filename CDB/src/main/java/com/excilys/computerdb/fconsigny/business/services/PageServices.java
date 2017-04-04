package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.datasource.JdbcDataSource;

@Service("pageService")
@Transactional
public class PageServices implements IPageServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();
  private JdbcTemplate jdbc;
  
  @Autowired
  JdbcDataSource datasource;

  @Autowired
  public PageServices(){}

  @Override
  public List<Computer> getComputer(IPage page) throws ServiceException {

    page.setMaxCount(computerDao.getCount(jdbc,page));
    return computerDao.findAllWithLimiter(jdbc,null, page.getLimite(), page.getOffset());
  }

  @Override
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException {
    page.setMaxCount(computerDao.getCount(jdbc,page));
    return computerDao.findAllWithLimiter(jdbc,null, page.getLimite(), page.getOffset());
  }
  
  public void setDataSource(DataSource dataSource) {
    this.jdbc = new JdbcTemplate(dataSource);
  }

}
