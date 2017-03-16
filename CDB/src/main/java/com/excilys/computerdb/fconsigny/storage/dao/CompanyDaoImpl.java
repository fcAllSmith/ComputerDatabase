package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.storage.database.DatabaseHelper;

public class CompanyDaoImpl implements CompanyDao {

	private static final String SELECT_ALL_COMPANY = "SELECT * FROM company;";
	private static final String SELECT_BY_ID = "SELECT * FROM company WHERE id =";
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";

	private static Logger logger = Logger.getLogger(CompanyDaoImpl.class);


	private Connection connection;
	public CompanyDaoImpl(Connection connection) {this.connection =connection;}

	@Override
	public List<Company> findAll() {
		ResultSet resQuery = new DatabaseHelper().queryGet(this.connection,SELECT_ALL_COMPANY);
		List<Company> companyList = new ArrayList<Company>();
		try {
			if (resQuery.isBeforeFirst() && !resQuery.isLast()) {
				while (resQuery.next()) {
					Company company = new Company(resQuery.getInt(COL_ID));
					company.setName(resQuery.getString(COL_NAME));
					companyList.add(company);
				}
			}
		} catch (SQLException error) {
			logger.error(error);
		}
		return companyList;
	}

	@Override
	public Company findById(long id){
		ResultSet resQuery = new DatabaseHelper().queryGet(this.connection,SELECT_BY_ID + id);
		try {
			if (resQuery.isBeforeFirst() && !resQuery.isLast()) {
				resQuery.next();
				Company company = new Company(resQuery.getInt(COL_ID));
				company.setName(resQuery.getString(COL_NAME));
				return company;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return null;
	}
}