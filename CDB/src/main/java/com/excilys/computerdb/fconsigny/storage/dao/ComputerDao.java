package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.entity.EntityComputer;

@Repository
public interface ComputerDao {

  public EntityComputer findById(Session session, final long id);

  public List<EntityComputer> findAll(Session session);

  public List<EntityComputer> findAllWithLimiter(Session session, final String name, final int limit, final int offset);

  public boolean addComputer(Session session, Computer computer);

  public boolean updateComputer(Session session, Computer computer);

  public boolean deleteComputer(Session session, final long id);
  
  public int getCount(Session session);
}
