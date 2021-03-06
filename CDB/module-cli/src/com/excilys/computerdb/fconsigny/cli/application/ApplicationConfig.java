package com.excilys.computerdb.fconsigny.cli.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({
	"com.excilys.computerdb.fconsigny.cli",
	"com.excilys.computerdb.fconsigny.service",
	"com.excilys.computerdb.fconsigny.persistence"})
public class ApplicationConfig {
  
}