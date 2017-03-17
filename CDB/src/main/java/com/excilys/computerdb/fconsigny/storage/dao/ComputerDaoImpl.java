package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.storage.database.DatabaseHelper;
import com.excilys.computerdb.fconsigny.storage.mapper.MysqlComputerMapper;
import com.excilys.computerdb.fconsigny.utils.db.DbUtil;

public class ComputerDaoImpl implements ComputerDao {

	private static Logger logger = Logger.getLogger(ComputerDaoImpl.class);

	private final static String QUERY_SELECT_ALL = "SELECT * FROM computer";
	private final static String QUERY_SELECT_BY_ID = "SELECT * FROM computer WHERE id =";
	private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=";

	public final static String COL_ID = "id"; 
	public final static String COL_NAME ="name";
	public final static String COL_INTRODUCED = "introduced";
	public final static String COL_DISCONTINUED ="discontinued";
	public final static String COL_COMPANY_ID = "company_id";

	private Connection connection;

	public ComputerDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Computer findById(final long id) {
		DatabaseHelper dm = new DatabaseHelper();
		ResultSet rs = dm.queryGet(this.connection,QUERY_SELECT_BY_ID + id);

		try {
			if(rs.isBeforeFirst()) {
				try {
					rs.next();
					return MysqlComputerMapper.resultSetIntoComputer(rs);
				} catch (SQLException error) {
					logger.error(error);
				}	
			}

		} catch (SQLException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public List<Computer> findAll() {
		DatabaseHelper dm = new DatabaseHelper();
		ResultSet rs = dm.queryGet(this.connection,QUERY_SELECT_ALL);
		List<Computer> computerList  = new ArrayList<Computer>();
		try {
			while (rs.next()) {
				Computer computer = MysqlComputerMapper.resultSetIntoComputer(rs);
				computerList.add(computer);
			}
		} catch (SQLException error) {
			logger.error(error);
		}
		
		return computerList;
	}

	@Override
	public List<Computer> findAllWithLimiter(final String name ,final int limit,final int offset) {
		DatabaseHelper dm = new DatabaseHelper();
		String query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + limit + " OFFSET " + offset + " WHERE name LIKE '%" + name + "%'";
		ResultSet rs = dm.queryGet(this.connection,query);
		List<Computer> computerList = new ArrayList<Computer>(); 

		try {
			while (rs.next()) {
				Computer computer = MysqlComputerMapper.resultSetIntoComputer(rs);
				computerList.add(computer);
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		try {
			rs.close();
		} catch (SQLException error) {
			logger.error(error);
		}
		return computerList;
	}

	@Override
	public boolean deleteComputer(long id) {
		DatabaseHelper dm = new DatabaseHelper();
		String query = QUERY_DELETE.concat(Integer.valueOf((int) id).toString()) ; 
		return dm.queryPost(this.connection,query);
	}

	public boolean insertComputer(Computer computer){
		return false;
	}

	@Override
	public boolean addComputer(Computer computer) {
		StringBuilder insertSQLBuilder = new StringBuilder();
		DatabaseHelper dh = new DatabaseHelper();
		insertSQLBuilder.append(" INSERT INTO computer ");
		insertSQLBuilder.append(" VALUES (0, ");
		insertSQLBuilder.append( computer.getName() + ",");
		insertSQLBuilder.append( computer.getIntroduced() + ",");
		insertSQLBuilder.append( computer.getDiscontinued() + ",");
		insertSQLBuilder.append( computer.getCompanyId());
		insertSQLBuilder.append(");"); 

		String query = insertSQLBuilder.toString();
		return dh.queryPost(this.connection, query);
	}

	@Override
	public boolean updateComputer(Computer computer){
		StringBuilder insertSQLBuilder = new StringBuilder();

		DatabaseHelper dh = new DatabaseHelper();
		insertSQLBuilder.append(" UPDATE computer ");
		insertSQLBuilder.append(" SET ");
		insertSQLBuilder.append("name = " + DbUtil.quote(computer.getName()));
		insertSQLBuilder.append(" ");
		insertSQLBuilder.append( computer.getName() + ",");
		insertSQLBuilder.append( computer.getIntroduced() + ",");
		insertSQLBuilder.append( computer.getDiscontinued() + ",");
		insertSQLBuilder.append( computer.getCompanyId());
		insertSQLBuilder.append(");"); 

		String query = insertSQLBuilder.toString(); 
		return dh.queryPost(this.connection, query); 
	}

}
