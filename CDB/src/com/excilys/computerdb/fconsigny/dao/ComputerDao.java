package com.excilys.computerdb.fconsigny.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.excilys.computerdb.fconsigny.database.DatabaseManager;
import com.excilys.computerdb.fconsigny.model.Computer;

public class ComputerDao {

	private static final String SELECT_ALL_COMPUTERS = "SELECT * FROM computer;";
	private static final String SELECT_BY_ID = "SELECT * FROM computer WHERE id =";
	private static final String COL_ID = "id";
	private static final String COL_NAME = "name";
	public ComputerDao() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Computer> querySelectAll(){
		ResultSet resQuery = new DatabaseManager().queryGet(SELECT_ALL_COMPUTERS);
		List<Computer> computerList = new ArrayList<Computer>();
		
		try {
			while(resQuery.next()){
				int comp_id = resQuery.getInt("id");
				computerList.add(new Computer((long)comp_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Result null");
		}
		
		return computerList;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
		
	}

}
