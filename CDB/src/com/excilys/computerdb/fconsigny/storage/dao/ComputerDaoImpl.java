package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.database.DatabaseManager;
import com.excilys.computerdb.fconsigny.storage.mapper.ComputerMapper;

public class ComputerDaoImpl implements ComputerDao {

  private final static String QUERY_SELECT_ALL = "SELECT * FROM computer;";
  private final static String QUERY_SELECT_BY_ID = "SELECT * FROM computer WHERE id =";
  private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=";

  public final static String COL_ID = "id"; 
  public final static String COL_NAME ="name";
  public final static String COL_INTRODUCED = "introduced";
  public final static String COL_DISCONTINUED ="discontinued";
  public final static String COL_COMPANY_ID = "company_id";

  public ComputerDaoImpl() {}

  @Override
  public Computer findById(long id) {
    DatabaseManager dm = new DatabaseManager();
    ResultSet rs = dm.queryGet(QUERY_SELECT_BY_ID + id);
    try {
      rs.next();
      Computer computer = ComputerMapper.resultSetIntoComputer(rs);
      return computer;
    } catch (SQLException error) {
    	error.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Computer> findAll() {
    DatabaseManager dm = new DatabaseManager();
    ResultSet rs = dm.queryGet(QUERY_SELECT_ALL);
    List<Computer> computerList  = new ArrayList<Computer>();
    try {
      while (rs.next()) {
        Computer computer = ComputerMapper.resultSetIntoComputer(rs);
        computerList.add(computer);
      }
    } catch (SQLException error) {
      error.printStackTrace();
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
      while (rs.next()) {
        Computer computer = ComputerMapper.resultSetIntoComputer(rs);
        computerList.add(computer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
	
    try {
      rs.close();
    } catch (SQLException error) {
      error.printStackTrace();
    }
    return computerList;
  }
	
  public boolean deleteById(int id) throws SQLException {
    DatabaseManager dm = new DatabaseManager();
    String query = QUERY_DELETE.concat(Integer.toString(id) + ";") ; 
    return dm.queryPost(query);
  }
	
  public boolean insertComputer(Computer computer){
    return false;
  }
	
}