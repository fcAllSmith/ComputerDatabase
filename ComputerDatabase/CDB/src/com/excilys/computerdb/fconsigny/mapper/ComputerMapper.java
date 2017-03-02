package com.excilys.computerdb.fconsigny.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.excilys.computerdb.fconsigny.dao.ComputerDaoImpl;
import com.excilys.computerdb.fconsigny.model.Computer;

public abstract class ComputerMapper {

  public static Computer resultSetIntoComputer(ResultSet resultSet){
    try {
      Computer computer = new Computer(resultSet.getLong(ComputerDaoImpl.COL_ID));
      computer.setName(resultSet.getString(ComputerDaoImpl.COL_NAME));
      computer.setIntroduced(LocalDateTime.ofInstant(resultSet.getTimestamp(ComputerDaoImpl.COL_INTRODUCED).toInstant(),ZoneId.systemDefault()));
      computer.setDiscontinued(LocalDateTime.ofInstant(resultSet.getTimestamp(ComputerDaoImpl.COL_DISCONTINUED).toInstant(),ZoneId.systemDefault()));
      computer.setCompanyId(resultSet.getLong(ComputerDaoImpl.COL_COMPANY_ID));
      return computer;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
	
  public static Computer stringIntoComputer(String[] args) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
    Computer computer = new Computer(Integer.parseInt(args[0]));
    computer.setName(args[1]);
    computer.setIntroduced(LocalDateTime.parse(args[2], formatter));
    computer.setDiscontinued(LocalDateTime.parse(args[3], formatter));
    computer.setCompanyId(Integer.parseInt(args[4]));
    return computer;
  }
}
