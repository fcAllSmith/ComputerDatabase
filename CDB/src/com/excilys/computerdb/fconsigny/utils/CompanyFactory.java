package com.excilys.computerdb.fconsigny.utils;

import com.excilys.computerdb.fconsigny.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.dao.CompanyDaoImpl;

public class CompanyFactory {

	public static CompanyDao getCompanyDao(){
		return new CompanyDaoImpl();
	}
}
