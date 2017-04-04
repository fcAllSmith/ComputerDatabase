package com.excilys.computerdb.fconsigny.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
/**
 * 
 * @author excilys In this file are define the bean.
 * 
 * 
 */
@Configuration
@ComponentScan({
  "com.excilys.computerdb.fconsigny.presentation.controller.cli",
  "com.excilys.computerdb.fconsigny.storage.datasource",
  "com.excilys.computerdb.fconsigny.presentation.view.cli",
  "com.excilys.computerdb.fconsigny.business.services",
  "com.excilys.computerdb.fconsigny.storage.connection",
  "com.excilys.computerdb.fconsigny.storage.dao"})
public class CliConfig {
  
  
}
