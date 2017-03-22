package com.excilys.computerdb.fconsigny.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.ICompanyServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IPageServices;
import com.excilys.computerdb.fconsigny.business.services.PageServices;
import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.connection.datasource.MysqlDatasource;

/**
 * 
 * @author excilys In this file are define the bean.
 */
@Configuration
public class ApplicationConfig {

  @Bean
  public IComputerServices iComputerServices() {
    return new ComputerServices();
  }

  @Bean
  public ICompanyServices iCompanyServices() {
    return new CompanyServices();
  }

  @Bean
  public IMysqlDatasource iMysqlConnection() {
    return new MysqlDatasource();
  }

  @Bean
  public IPageServices iPageServices() {
    return new PageServices();
  }

}
