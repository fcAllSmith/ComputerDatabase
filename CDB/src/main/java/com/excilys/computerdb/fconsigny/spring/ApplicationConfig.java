package com.excilys.computerdb.fconsigny.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.ICompanyServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;

@Configuration
@AnnotationDrivenTx
public class ApplicationConfig {
	
	@Bean 
	public IComputerServices iComputerServices(){
		return new ComputerServices();
	}
	
	@Bean 
	public ICompanyServices iCompanyServices(){
		return new CompanyServices();
	}
	
}
