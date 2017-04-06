package com.excilys.computerdb.fconsigny.storage.dao;


import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;
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
    return query.getFirstResult();
  }

  @Override
  public EntityComputer findById(Session session, final long id){
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_SELECT_BY_ID");
    Query<EntityComputer> query = session.createQuery(str_query,EntityComputer.class)
        .setParameter("id", ((Number) id).intValue() ); 

    return query.getSingleResult();

  }

  @Override
  public List<EntityComputer> findAll(Session session) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("QUERY_SELECT_ALL");
    Query<EntityComputer> query = session.createQuery(str_query,EntityComputer.class);
    return query.getResultList();
  }

  @Override
  public List<EntityComputer> findAllWithLimiter(Session session, String name, final int limit, final int offset) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);   
    String str_query; 

    if (name == null) {
      str_query = properties.getProperty("QUERY_SELECT_WITH_LIMITER");

    } else {
      str_query = properties.getProperty("QUERY_SELECT_WITH_LIMITER_AND_FILTER");
    }
    
    Query<EntityComputer> query = session.createQuery(str_query,EntityComputer.class)
        .setFirstResult(offset).setMaxResults(limit); 

    return query.getResultList();
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
}
