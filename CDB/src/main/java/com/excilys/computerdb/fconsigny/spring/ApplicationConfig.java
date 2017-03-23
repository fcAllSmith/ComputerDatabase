package com.excilys.computerdb.fconsigny.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
 

import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;


/**
 * 
 * @author excilys In this file are define the bean.
 * 
 * 
 */
@Configuration
@ComponentScan(basePackages ={"com.excilys.computerdb.fconsigny.business.services"})
public class ApplicationConfig {
  
}
