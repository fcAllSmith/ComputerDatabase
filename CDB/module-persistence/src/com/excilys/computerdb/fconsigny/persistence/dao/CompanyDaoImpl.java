package com.excilys.computerdb.fconsigny.persistence.dao;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.persistence.utils.FilePropertyLoader;

import com.excilys.computerdb.fconsigny.persistence.entity.EntityCompany;

@Repository
public class CompanyDaoImpl implements CompanyDao {

  private static final String PROPERTY_FILE = "company.properties";

  @Override
  public List<EntityCompany> findAll(Session session) {
    Properties properties = FilePropertyLoader.loadSqlProperties(CompanyDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("SELECT_ALL");
    Query<EntityCompany> query  = session.createQuery(str_query, EntityCompany.class);
    return (List<EntityCompany>) query.getResultList();
  }

  @Override
  public EntityCompany findById(Session session, final long id) {
    Properties properties = FilePropertyLoader.loadSqlProperties(CompanyDaoImpl.class, PROPERTY_FILE);
    String str_query = properties.getProperty("SELECT_BY_ID");
    Query<EntityCompany> query = session.createQuery(str_query,EntityCompany.class).setParameter("id", ((Number) id).intValue());
    return (EntityCompany) query.getSingleResult();
  }
  
}
