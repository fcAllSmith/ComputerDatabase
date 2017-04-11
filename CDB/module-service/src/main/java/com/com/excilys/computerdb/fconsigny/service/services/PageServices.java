package com.excilys.computerdb.fconsigny.service.services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import com.excilys.computerdb.fconsigny.service.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.core.model.Computer;
import com.excilys.computerdb.fconsigny.persistence.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.persistence.entity.EntityComputer;
import com.excilys.computerdb.fconsigny.service.hibernate.HibernateUtil;

import com.excilys.computerdb.fconsigny.core.components.IPage;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pageService")
public class PageServices implements IPageServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();

  @Autowired
  public PageServices(){}

  @Override
  public List<Computer> getComputer(IPage page) throws ServiceException {
    Session session  = HibernateUtil.getSessionFactory().openSession();
    page.setMaxCount(computerDao.getCount(session));
    List<Computer> computerList =  computerDao.findAllWithLimiter(session,null, page.getLimite(), page.getOffset());

    
    return computerList;
  }

  @Override
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession(); 
    List<Computer> entityList = computerDao.findAllWithLimiter(session, null, page.getLimite(), page.getOffset());
    return entityList;
  }

}
