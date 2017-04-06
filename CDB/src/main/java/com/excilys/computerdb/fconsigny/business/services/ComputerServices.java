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
    Computer computer = computerDao.findById(session, id);
    return computer;
  }

  public List<Computer> getAllComputers() throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession(); 
    List<Computer> computerList = computerDao.findAll(session);
    return computerList; 
  }

  public List<Computer> getAllComputersWithLimiter (final int offset, final int limit, final String name) throws ServiceException {
    Session session = HibernateUtil.getSessionFactory().openSession(); 
    List<Computer> computerList = computerDao.findAllWithLimiter(session, name, limit, offset);
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

}
