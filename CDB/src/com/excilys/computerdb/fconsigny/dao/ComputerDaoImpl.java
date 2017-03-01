package com.excilys.computerdb.fconsigny.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.fconsigny.database.DatabaseManager;
import com.excilys.computerdb.fconsigny.model.Computer;

public class ComputerDaoImpl implements ComputerDao {

	private final static String QUERY_SELECT_ALL = "SELECT * FROM computer;";
	private final static String QUERY_SELECT_BY_ID = "SELECT * FROM computer WHERE id =";
	private final static String COL_ID = "id"; 
	private final static String COL_NAME ="name";
	private final static String COL_INTRODUCED = "introduced";
	private final static String COL_DISCONTINUED ="discontinued";
	private final static String COL_COMPANY_ID = "company_id";

	public ComputerDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Computer findById(long id) {
		// TODO Auto-generated method stub
		DatabaseManager dm = new DatabaseManager();
		ResultSet rs = dm.queryGet(QUERY_SELECT_BY_ID + id);

		try {
			rs.next();
			try{
				Computer computer = new Computer(rs.getLong(COL_ID));
				computer.setName(rs.getString(COL_NAME));
				//computer.setIntroduced(LocalDateTime.ofInstant(rs.getTimestamp(COL_INTRODUCED).toInstant(),ZoneId.systemDefault()));
				//computer.setDiscontinued(LocalDateTime.ofInstant(rs.getTimestamp(COL_DISCONTINUED).toInstant(),ZoneId.systemDefault()));
				computer.setCompanyId(rs.getLong(COL_COMPANY_ID));
				// TODO Auto-generated catch block
				return computer;
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Computer> findAll() {
		// TODO Auto-generated method stub
		DatabaseManager dm = new DatabaseManager();
		ResultSet rs = dm.queryGet(QUERY_SELECT_ALL);
		List<Computer> computerList  = new ArrayList<Computer>();
		try {
			while(rs.next()){
				Computer computer = new Computer(rs.getLong(COL_ID));
				computer.setName(rs.getString(COL_NAME));
				//computer.setIntroduced(LocalDateTime.ofInstant(rs.getTimestamp(COL_INTRODUCED).toInstant(),ZoneId.systemDefault()));
				//computer.setDiscontinued(LocalDateTime.ofInstant(rs.getTimestamp(COL_DISCONTINUED).toInstant(),ZoneId.systemDefault()));
				computer.setCompanyId(rs.getLong(COL_COMPANY_ID));
				computerList.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerList;
	}
}
