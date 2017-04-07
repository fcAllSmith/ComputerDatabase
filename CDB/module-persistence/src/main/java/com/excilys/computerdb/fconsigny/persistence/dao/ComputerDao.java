package src.main.java.com.excilys.computerdb.fconsigny.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerDao {

  public Computer findById(Session session, final long id);

  public List<Computer> findAll(Session session);

  public List<Computer> findAllWithLimiter(Session session, final String name, final int limit, final int offset);

  public boolean addComputer(Session session, Computer computer);

  public boolean updateComputer(Session session, Computer computer);

  public boolean deleteComputer(Session session, final long id);
  
  public int getCount(Session session);
}
