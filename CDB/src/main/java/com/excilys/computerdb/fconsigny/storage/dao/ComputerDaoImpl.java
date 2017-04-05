package com.excilys.computerdb.fconsigny.storage.dao;


import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;
import com.excilys.computerdb.fconsigny.utils.property.FilePropertyLoader;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDaoImpl implements ComputerDao {

  private static Logger logger = Logger.getLogger(ComputerDaoImpl.class);

  private static final String PROPERTY_FILE = "computer.properties";
  public final static String COL_ID = "id";
  public final static String COL_NAME = "name";
  public final static String COL_INTRODUCED = "introduced";
  public final static String COL_DISCONTINUED = "discontinued";
  public final static String COL_COMPANY_ID = "company_id";

  @Override
  public int getCount(JdbcTemplate jdbcTemplate, IPage page) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("QUERY_GET_COUNT");
    return jdbcTemplate.queryForObject(query,Integer.class);
  }

  @Override
  public Computer findById( JdbcTemplate jdbcTemplate, final long id){
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("QUERY_SELECT_BY_ID");
    Object[] params = {id};
    System.out.println(query);
    return (Computer) jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<Computer>(Computer.class),params);
  }

  @Override
  public List<Computer> findAll(JdbcTemplate jdbcTemplate) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("QUERY_SELECT_ALL");
    return jdbcTemplate.query(query,new BeanPropertyRowMapper<Computer>(Computer.class));
  }

  @Override
  public List<Computer> findAllWithLimiter(JdbcTemplate jdbcTemplate, String name, final int limit, final int offset) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);   

    if (name == null) {
      String query = properties.getProperty("QUERY_SELECT_WITH_LIMITER");
      Object[] params = {limit,offset};
      return jdbcTemplate.query(query,new BeanPropertyRowMapper<Computer>(Computer.class),params);
    } else {

      Object[] params = {limit,offset,name};
      String query = properties.getProperty("QUERY_SELECT_WITH_LIMITER_AND_FILTER");
      return  jdbcTemplate.query(query,new BeanPropertyRowMapper<Computer>(Computer.class),params);
    }
  }

  @Override
  public boolean deleteComputer(JdbcTemplate jdbcTemplate ,long id) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("QUERY_DELETE"); 
    Object[] params = {id};
    return ( jdbcTemplate.queryForObject(query,Integer.class,params) > 0);
  }

  @Override
  public boolean addComputer(JdbcTemplate jdbcTemplate, Computer computer) {
    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("QUERY_ADD"); 
    Object[] params  = {computer.getName(),computer.getIntroduced(),computer.getDiscontinued(),computer.getCompany().getId()}; 
    return ( jdbcTemplate.query(query,new BeanPropertyRowMapper<Computer>(Computer.class),params) != null); 
  }

  @Override
  public boolean updateComputer(JdbcTemplate jdbcTemplate,Computer computer) {

    Properties properties = FilePropertyLoader.loadSqlProperties(ComputerDaoImpl.class, PROPERTY_FILE);
    String query = properties.getProperty("QUERY_UPDATE"); 
    Object[] params = {computer.getName(),computer.getIntroduced(),computer.getDiscontinued(),computer.getCompany().getId(),computer.getId()};
    return  jdbcTemplate.query(query,new BeanPropertyRowMapper<Computer>(Computer.class),params) != null ;
  }
}
