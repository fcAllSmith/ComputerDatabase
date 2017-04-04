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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("computerDao")
public class ComputerDaoImpl implements ComputerDao {

  private static Logger logger = Logger.getLogger(ComputerDaoImpl.class);

  private final static String QUERY_SELECT_ALL = "SELECT * FROM computer LEFT JOIN company ON company.id = computer.company_id";
  private final static String QUERY_SELECT_BY_ID = "SELECT * FROM computer WHERE id = ? LEFT JOIN company ON company.id = computer.company_id";
  private final static String QUERY_DELETE = "DELETE FROM computer WHERE id=";
  private final static String QUERY_GET_COUNT = "SELECT COUNT(*) AS maxCount FROM computer";

  public final static String COL_ID = "id";
  public final static String COL_NAME = "name";
  public final static String COL_INTRODUCED = "introduced";
  public final static String COL_DISCONTINUED = "discontinued";
  public final static String COL_COMPANY_ID = "company_id";

  @Override
  public int getCount(JdbcTemplate jdbcTemplate) {
    return jdbcTemplate.queryForObject(QUERY_GET_COUNT,Integer.class);
  }

  @Override
  public Computer findById( JdbcTemplate jdbcTemplate, final long id){
    return (Computer) jdbcTemplate.queryForObject("SELECT * FROM computer WHERE id = " + id + " LEFT JOIN company ON company.id = computer.company_id",
        new BeanPropertyRowMapper(Computer.class));
  }

  @Override
  public List<Computer> findAll(JdbcTemplate jdbcTemplate) {
    return jdbcTemplate.query(QUERY_SELECT_ALL,new BeanPropertyRowMapper(Computer.class));
  }

  @Override
  public List<Computer> findAllWithLimiter(JdbcTemplate jdbcTemplate, final String name, final int limit, final int offset) {
    String query = null;
    if (name == null) {
      query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + limit + " OFFSET " + offset;
      System.out.println(query);
    } else {
      // query = "SELECT * FROM computer ORDER BY id ASC LIMIT " + offset + ","
      // + limit + " WHERE name LIKE '%" + name + "%'";
      query = "SELECT * FROM computer WHERE name LIKE '%" + name + "%'";
    }
    List<Computer> computerList = jdbcTemplate.query(query,new BeanPropertyRowMapper(Computer.class));
    return computerList;
  }

  @Override
  public boolean deleteComputer(JdbcTemplate jdbcTemplate ,long id) {
    return ( jdbcTemplate.queryForObject(QUERY_DELETE + Integer.valueOf((int) id).toString(),Integer.class) > 0);
  }

  @Override
  public boolean addComputer(JdbcTemplate jdbcTemplate, Computer computer) {
    StringBuilder insertSQLBuilder = new StringBuilder();
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
    try {
      return DatabaseHelper.queryPost(jdbcTemplate.getDataSource().getConnection(), query);
    } catch (SQLException e) {
      return false;
    }
  }

  @Override
  public boolean updateComputer(JdbcTemplate jdbcTemplate,Computer computer) {
    StringBuilder insertSQLBuilder = new StringBuilder();

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
    try {
      return DatabaseHelper.queryPost(jdbcTemplate.getDataSource().getConnection(), query);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
