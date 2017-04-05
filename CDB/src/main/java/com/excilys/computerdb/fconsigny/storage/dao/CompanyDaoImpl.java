package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.storage.entity.EntityCompany;

@Repository
public class CompanyDaoImpl implements CompanyDao {

  private static final String PROPERTY_FILE = "company.properties";
  private static final String COL_ID = "id";
  private static final String COL_NAME = "name";

  private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);

  @Override
  public List<EntityCompany> findAll(Session session) {
    
   /* Properties properties = FilePropertyLoader.loadSqlProperties(CompanyDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("SELECT_ALL");
    return (List<Company>) jdbc.queryForObject(query,  new BeanPropertyRowMapper(Company.class)); */

    Query<EntityCompany> query  = session.createQuery("from " + EntityCompany.class.getName(), EntityCompany.class);
    return (List<EntityCompany>) query.getResultList();
    //Transaction t = session.beginTransaction() ; 
  }

  @Override
  public EntityCompany findById(Session session, final long id) {
    
   /* Properties properties = FilePropertyLoader.loadSqlProperties(CompanyDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("SELECT_BY_ID");
    return (Company) jdbc.queryForObject(query,  new BeanPropertyRowMapper(Company.class));*/
    
    org.hibernate.Query query = session.createQuery("select id,nale from company where id = :id "); 
    query.setParameter(":id", id);
    return (EntityCompany) ((Query) query).getSingleResult();
  }
  
}
