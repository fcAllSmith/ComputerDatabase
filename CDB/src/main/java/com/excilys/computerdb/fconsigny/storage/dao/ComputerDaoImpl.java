package com.excilys.computerdb.fconsigny.storage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.storage.database.DatabaseHelper;
import com.excilys.computerdb.fconsigny.storage.mapper.MysqlComputerMapper;
import com.excilys.computerdb.fconsigny.utils.db.DbUtil;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class ComputerDaoImpl implements ComputerDao {

  private static Logger logger = Logger.getLogger(ComputerDaoImpl.class);

  private final static String QUERY_SELECT_ALL = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id";
  private final static String QUERY_SELECT_BY_ID = "SELECT * FROM computer WHERE id =";
  private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=";

  public final static String COL_ID = "id";
  public final static String COL_NAME = "name";
  public final static String COL_INTRODUCED = "introduced";
  public final static String COL_DISCONTINUED = "discontinued";
  public final static String COL_COMPANY_ID = "company_id";


  public ComputerDaoImpl() {
  
  }

  @Override
  public Computer findById(Connection connection final long id) {
    DatabaseHelper dm = new DatabaseHelper();
    ResultSet rs = dm.queryGet(connection, QUERY_SELECT_BY_ID + id);

    try {
      if (rs.isBeforeFirst()) {
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
  public List<Computer> findAll(Connection connection) {
    DatabaseHelper dm = new DatabaseHelper();
    ResultSet rs = dm.queryGet(connection, QUERY_SELECT_ALL);
    List<Computer> computerList = new ArrayList<Computer>();
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
  public List<Computer> findAllWithLimiter(Connection connection, final String name, final int limit, final int offset) {
    DatabaseHelper dm = new DatabaseHelper();
    String query = null;
    if (name == null) {
      query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + limit + " OFFSET " + offset;
      System.out.println(query);
    } else {
      // query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + offset + ","
      // + limit + " WHERE name LIKE '%" + name + "%'";
      query = "SELECT * FROM computer WHERE name LIKE '%" + name + "%'";
    }
    ResultSet rs = dm.queryGet(connection, query);
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
  public boolean deleteComputer(Connection connection ,long id) {
    DatabaseHelper dm = new DatabaseHelper();
    String query = QUERY_DELETE.concat(Integer.valueOf((int) id).toString());
    return dm.queryPost(connection, query);
  }

  @Override
  public boolean addComputer(Connection connection, Computer computer) {
    StringBuilder insertSQLBuilder = new StringBuilder();
    DatabaseHelper dh = new DatabaseHelper();
    insertSQLBuilder.append(" INSERT INTO computer (name,introduced,discontinued,company_id)");
    insertSQLBuilder.append(" VALUES ( '");
    insertSQLBuilder.append(computer.getName() + "',");
    if (computer.getIntroduced() == null) {
      insertSQLBuilder.append("NULL" + ",");
    } else {
      insertSQLBuilder.append("'" + Timestamp.valueOf(computer.getIntroduced()) + "'" + ",");
    }

    if (computer.getDiscontinued() == null) {
      insertSQLBuilder.append("NULL" + ",");
    } else {
      insertSQLBuilder.append("'" + Timestamp.valueOf(computer.getDiscontinued()) + "'" + ",");
    }

    insertSQLBuilder.append(computer.getCompany().getId());
    insertSQLBuilder.append(");");

    String query = insertSQLBuilder.toString();
    return dh.queryPost(connection, query);
  }

  @Override
  public boolean updateComputer(Connection connection,Computer computer) {
    StringBuilder insertSQLBuilder = new StringBuilder();

    DatabaseHelper dh = new DatabaseHelper();
    insertSQLBuilder.append(" UPDATE computer ");
    insertSQLBuilder.append(" SET ");
    insertSQLBuilder.append("name=" + DbUtil.quote(computer.getName()) + ",");
    if (computer.getIntroduced() == null) {
      insertSQLBuilder.append("introduced=" + "NULL" + ",");
    } else {
      insertSQLBuilder.append("introduced=" + "'" + Timestamp.valueOf(computer.getIntroduced()) + "'" + ",");
    }

    if (computer.getDiscontinued() == null) {
      insertSQLBuilder.append("discontinued=" + "NULL" + ",");
    } else {
      insertSQLBuilder.append("discontinued=" + "'" + Timestamp.valueOf(computer.getDiscontinued()) + "'" + ",");
    }
    insertSQLBuilder.append(" company_id=  " + computer.getCompany().getId());
    insertSQLBuilder.append(" WHERE id=" + computer.getId());

    System.out.println(insertSQLBuilder.toString());
    String query = insertSQLBuilder.toString();
    return dh.queryPost(connection, query);
  }
}
