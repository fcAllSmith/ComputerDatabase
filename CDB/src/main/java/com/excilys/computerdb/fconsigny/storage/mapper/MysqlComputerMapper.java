package com.excilys.computerdb.fconsigny.storage.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDaoImpl;
import com.excilys.computerdb.fconsigny.storage.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.storage.validator.MysqlComputerValidator;

public abstract class MysqlComputerMapper extends MysqlComputerValidator {

  private static Logger logger = Logger.getLogger(MysqlComputerMapper.class);

  public static Computer resultSetIntoComputer(final ResultSet resultSet) {
   /* MysqlComputerValidator validator = new MysqlComputerValidator();
    try {
      Computer computer = new Computer((int)validator.setValidId(resultSet.getInt(ComputerDaoImpl.COL_ID)));
      computer.setName(validator.setValidName(resultSet.getString(ComputerDaoImpl.COL_NAME)));
      computer.setIntroduced(validator.setValideDate((resultSet.getTimestamp(ComputerDaoImpl.COL_INTRODUCED))));
      computer.setDiscontinued(validator.setValideDate((resultSet.getTimestamp(ComputerDaoImpl.COL_DISCONTINUED))));
      Company company = new Company(resultSet.getLong("company_id"));
      company.setName(resultSet.getString("name"));
      computer.setCompany(company);
      // System.out.println(resultSet.getInt("total"));

      return computer;
    } catch (SQLException e) {
      logger.error(e);
    } catch (ComputerException e) {
      logger.error(e);
    }*/

    return null;
  }

  public static Computer stringIntoComputer(final String[] args) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");
    Computer computer = new Computer(Integer.parseInt(args[0]));
    computer.setName(args[1]);
    computer.setIntroduced(LocalDateTime.parse(args[2], formatter));
    computer.setDiscontinued(LocalDateTime.parse(args[3], formatter));
    // computer.setCompanyId(Integer.parseInt(args[4]));
    return computer;
  }
}
