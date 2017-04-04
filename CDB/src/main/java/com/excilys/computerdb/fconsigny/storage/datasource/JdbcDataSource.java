package com.excilys.computerdb.fconsigny.storage.datasource;

import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.excilys.computerdb.fconsigny.utils.property.FilePropertyLoader;


@Repository
public class JdbcDataSource {

  public JdbcDataSource() { 

  }
  
  public DriverManagerDataSource getDataSource(){
    
    Properties properties = FilePropertyLoader.loadDatabaseProperties(JdbcDataSource.class, "db.properties");
    
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(properties.getProperty("DB_DRVER_CLASS"));
    dataSource.setUrl(properties.getProperty("DB_URL"));
    dataSource.setUsername(properties.getProperty("DB_USERNAME"));
    dataSource.setPassword(properties.getProperty("DB_PASSWORD"));
    
    return dataSource; 
  }
  

}
