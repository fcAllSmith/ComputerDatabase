package com.excilys.computerdb.fconsigny.business.services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
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
import com.excilys.computerdb.fconsigny.storage.entity.EntityComputer;
import com.excilys.computerdb.fconsigny.utils.hibernate.HibernateUtil;

@Service("pageService")
public class PageServices implements IPageServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();
  private JdbcTemplate jdbc;

  @Autowired
  JdbcDataSource datasource;

  @Autowired
  public PageServices(){}

  @Override
  public List<Computer> getComputer(IPage page) throws ServiceException {
    Session session  = HibernateUtil.getSessionFactory().openSession();
    page.setMaxCount(computerDao.getCount(session));
    List<EntityComputer> entityList =  computerDao.findAllWithLimiter(session,null, page.getLimite(), page.getOffset());
    List<Computer> computerList = new ArrayList<Computer>(); 

    for(EntityComputer entity : entityList){
      Computer computer = new Computer(entity.getId()); 
      computer.setName(entity.getName());
      computer.setIntroduced(entity.getIntroduced());
      computer.setDiscontinued(entity.getDiscontinued());
      computerList.add(computer);

    }
    return computerList;
  }

  @Override
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession(); 
    List<EntityComputer> entityList = computerDao.findAllWithLimiter(session, null, page.getLimite(), page.getOffset());
    return null;
  }

  public void setDataSource(DataSource dataSource) {
    this.jdbc = new JdbcTemplate(dataSource);
  }

}
