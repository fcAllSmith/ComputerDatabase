package com.excilys.computerdb.fconsigny.storage.dao;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.utils.property.FilePropertyLoader;

@Repository
public class CompanyDaoImpl implements CompanyDao {

  private static final String PROPERTY_FILE = "company.properties";
  private static final String COL_ID = "id";
  private static final String COL_NAME = "name";

  private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);

  @Override
  public List<Company> findAll(JdbcTemplate jdbc) {
    
    Properties properties = FilePropertyLoader.loadSqlProperties(CompanyDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("SELECT_ALL");
    return (List<Company>) jdbc.queryForObject(query,  new BeanPropertyRowMapper(Company.class));
  }

  @Override
  public Company findById(JdbcTemplate jdbc, final long id) {
    
    Properties properties = FilePropertyLoader.loadSqlProperties(CompanyDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("SELECT_BY_ID");
    return (Company) jdbc.queryForObject(query,  new BeanPropertyRowMapper(Company.class));
  }
  
}
