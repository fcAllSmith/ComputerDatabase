package com.excilys.computerdb.fconsigny.persistence.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.persistence.entity.EntityComputer;
import com.excilys.computerdb.fconsigny.persistence.entity.EntityCompany;

import com.excilys.computerdb.fconsigny.core.model.Computer;
import com.excilys.computerdb.fconsigny.persistence.utils.FilePropertyLoader;


@Repository
public class ComputerDaoImpl implements ComputerDao {

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
    Transaction tx = session.beginTransaction();
    Query query = session.createQuery(str_query)
        .setParameter("id", ((Number) id).intValue());

    int result = query.executeUpdate();
    tx.commit();
    
    return (result > 0);
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
    Transaction tx = session.beginTransaction();

    Query query = session.createQuery(str_query);
    query.setParameter("name", computer.getName());
    query.setParameter("introduced", computer.getIntroduced());
    query.setParameter("discontinued", computer.getDiscontinued());
    query.setParameter("company_id", ((Number) computer.getCompany().getId()).intValue());
    query.setParameter("id", ((Number) computer.getId()).intValue());

    int result = query.executeUpdate();
    tx.commit();

    return (result > 0); 
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
