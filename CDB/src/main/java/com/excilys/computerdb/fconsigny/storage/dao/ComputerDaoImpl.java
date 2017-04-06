package com.excilys.computerdb.fconsigny.storage.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;
import com.excilys.computerdb.fconsigny.storage.entity.EntityCompany;
import com.excilys.computerdb.fconsigny.storage.entity.EntityComputer;
import com.excilys.computerdb.fconsigny.utils.property.FilePropertyLoader;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImpl implements ComputerDao {

  private static Logger logger = Logger.getLogger(ComputerDaoImpl.class);

  private static final String PROPERTY_FILE = "computer.properties";

  @Override
  public int getCount(Session session) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_GET_COUNT");
    Query query = session.createQuery(str_query);
    long sizeMax = (long)query.getSingleResult();
    System.out.println("SIZE MAX");

    return Math.toIntExact(sizeMax);
  }

  @Override
  public Computer findById(Session session, final long id){
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_SELECT_BY_ID");
    List<Object[]> query = session.createQuery(str_query)
        .setParameter("id", ((Number) id).intValue() ).list(); 

    Computer computer = null;
    for (Object[] res : query){
      computer = fillComputer(res);
    }
    return computer;

  }

  @Override
  public List<Computer> findAll(Session session) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_SELECT_ALL");

    List<Object[]> result = session.createQuery(str_query).list();
    List<Computer> computerList = new ArrayList<Computer>();

    for (Object[] res : result ){
      computerList.add(fillComputer(res));
    }

    return computerList;
  }

  @Override
  public List<Computer> findAllWithLimiter(Session session, String name, final int limit, final int offset) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);   
    String str_query; 
    List<Object[]> query ;

    if (name == null) {
      str_query = properties.getProperty("QUERY_SELECT_WITH_LIMITER");
      query = session.createQuery(str_query)
          .setFirstResult(offset).setMaxResults(limit).list(); 

    } else {
      str_query = properties.getProperty("QUERY_SELECT_WITH_LIMITER_AND_FILTER");
      query = session.createQuery(str_query)
          .setParameter("name",name)
          .setFirstResult(offset).setMaxResults(limit).list(); 
    }

    List<Computer> computerList = new ArrayList<Computer>();
    for(Object[] res : query){
      computerList.add(fillComputer(res));
    }
    return computerList;
  }

  @Override
  public boolean deleteComputer(Session session ,long id) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_DELETE"); 
    return false;
  }

  @Override
  public boolean addComputer(Session session, Computer computer) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_ADD"); 
    return false;
  }

  @Override
  public boolean updateComputer(Session session,Computer computer) {

    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_UPDATE"); 
    return false; 
  }

  private Computer fillComputer(Object[] res){
    EntityComputer entityComputer = (EntityComputer) res[0];
    Computer computer;

    if(res[1] != null) {
      EntityCompany entityCompany = (EntityCompany) res[1];
      computer = new Computer( 
          entityComputer.getId(),
          entityComputer.getName(),
          entityComputer.getIntroduced(),
          entityComputer.getDiscontinued(),
          entityCompany.getId(),
          entityCompany.getName()
          );

    } else {
      computer = new Computer(entityComputer.getId());
      computer.setName(entityComputer.getName());
      computer.setIntroduced(entityComputer.getIntroduced());
      computer.setDiscontinued(entityComputer.getDiscontinued());
    }

    return computer;
  }
}
