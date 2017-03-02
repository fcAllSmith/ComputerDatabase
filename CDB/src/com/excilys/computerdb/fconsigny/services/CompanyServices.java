package com.excilys.computerdb.fconsigny.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.excilys.computerdb.fconsigny.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.exceptions.CompanyException;
import com.excilys.computerdb.fconsigny.model.Company;
import com.excilys.computerdb.fconsigny.utils.CompanyFactory;

public class CompanyServices {

	private CompanyDao cDao =  CompanyFactory.getCompanyDao();

	public Company getUniqueCompany(long id) throws CompanyException{
		try {
			return cDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CompanyException("Company not found");
		}
	}
	public List<Company> getAllCompanies(){
		try {
			return cDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyList();
		} 
	}
}
