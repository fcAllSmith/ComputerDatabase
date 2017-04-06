package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.ComputerFactory;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDao;
import com.excilys.computerdb.fconsigny.storage.datasource.JdbcDataSource;
import com.excilys.computerdb.fconsigny.storage.entity.EntityComputer;
import com.excilys.computerdb.fconsigny.utils.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComputerServices implements IComputerServices {

  private final ComputerDao computerDao = ComputerFactory.getComputerDao();

  public ComputerServices() {
   
  }
  public Computer getUniqueComputer(final long id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    EntityComputer entity = computerDao.findById(session, id);
    System.out.println(" " + entity.getCompanyId());
    return fillComputer(entity);
    
  }

  public List<Computer> getAllComputers() throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession(); 
    List<EntityComputer> entityList = computerDao.findAll(session);
    List<Computer> computerList = new ArrayList<Computer>(); 
    for(EntityComputer entity : entityList){
      computerList.add(fillComputer(entity));
    }
    
    return computerList; 
  }

  public List<Computer> getAllComputersWithLimiter (final int offset, final int limit, final String name) throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession(); 
    List<EntityComputer> entityList = computerDao.findAllWithLimiter(session, name, limit, offset);
    List<Computer> computerList = new ArrayList<Computer>() ;
    
    for(EntityComputer entity : entityList){
      computerList.add(fillComputer(entity));
    }
    
    return computerList;
  }

  /**
   * 
   * @param computerDto
   * @return true if successful.
   */
  public boolean saveComputer(final Computer computer) throws ServiceException {
    return false;
  }

  public boolean editComptuter(final Computer computer) throws ServiceException {
   return false;
  }

  public boolean deleteComputer(final long id) throws ServiceException{
  return false;
  }

  public Computer fillComputer(EntityComputer comp){
    Computer computer = new Computer(comp.getId()); 
    computer.setName(comp.getName());
    return computer; 
  }
}
