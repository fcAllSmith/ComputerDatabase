package com.excilys.computerdb.fconsigny.storage.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.storage.dao.ComputerDaoImpl;
import com.excilys.computerdb.fconsigny.storage.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.storage.validator.MysqlComputerValidator;

public abstract class MysqlComputerMapper extends MysqlComputerValidator{


	public static Computer resultSetIntoComputer(ResultSet resultSet){
		MysqlComputerValidator validator = new MysqlComputerValidator();
		try {
			Computer computer = new Computer(validator.setValidId(resultSet.getLong(ComputerDaoImpl.COL_ID)));
			computer.setName(validator.setValidName( resultSet.getString(ComputerDaoImpl.COL_NAME)));
			computer.setIntroduced(validator.setValideDate((resultSet.getTimestamp(ComputerDaoImpl.COL_INTRODUCED))));
			computer.setDiscontinued(validator.setValideDate((resultSet.getTimestamp(ComputerDaoImpl.COL_DISCONTINUED))));
			computer.setCompanyId(resultSet.getLong(ComputerDaoImpl.COL_COMPANY_ID));
			return computer;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ComputerException e) {
			e.printStackTrace();
		}
		
		return null;
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
