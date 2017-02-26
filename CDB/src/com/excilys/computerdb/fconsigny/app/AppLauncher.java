package com.excilys.computerdb.fconsigny.app;

import com.excilys.computerdb.fconsigny.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.dao.ComputerDao;

public class AppLauncher {

	public static void main(String[] args) {
		System.out.println("-- Welcom  to Computer Database");
		ComputerDao computerDao = new ComputerDao ();
		computerDao.querySelectAll();
		computerDao.querySelectById(50);
		CompanyDao companyDao = new CompanyDao();
		companyDao.querySelectAll();
		companyDao.querySelectById(10);
	}
}
