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

	public ComputerDaoImpl() {}

	@Override
	public Computer findById(long id) {
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

	@Override
	public List<Computer> findAllWithLimiter(int limit,int offset) {
		DatabaseManager dm = new DatabaseManager();
		String query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + limit + " OFFSET " + offset;
		ResultSet rs = dm.queryGet(query);
		List<Computer> computerList = new ArrayList<Computer>(); 
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
