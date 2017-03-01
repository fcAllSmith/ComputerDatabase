package com.excilys.computerdb.fconsigny.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.fconsigny.database.DatabaseManager;
import com.excilys.computerdb.fconsigny.model.Company;

public class CompanyDao {

	private static final String SELECT_ALL_COMPUTERS = "SELECT * FROM company;";
	private static final String SELECT_BY_ID = "SELECT * FROM company WHERE id =";
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	
	public CompanyDao() {}
	
	public List<Company> querySelectAll(){
		ResultSet resQuery = new DatabaseManager().queryGet(SELECT_ALL_COMPUTERS);
		List<Company> companyList = new ArrayList<Company>();
		
		try {
			while(resQuery.next()){
				int comp_id = resQuery.getInt("id");
				companyList.add(new Company((long)comp_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Result null");
		}
		
		return companyList;
	}
	
	public boolean querySelectById(long id){
		ResultSet resQuery = new DatabaseManager().queryGet(SELECT_BY_ID + id);
		try {
			if(resQuery.isBeforeFirst()){
				System.out.println("has result");
				return true; 
			}else{
				System.out.println("has no result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
