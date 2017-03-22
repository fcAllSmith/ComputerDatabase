package com.excilys.computerdb.fconsigny.storage.validator;

import com.excilys.computerdb.fconsigny.storage.exceptions.ComputerException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MysqlComputerValidator {

  public long setValidId(long id) throws ComputerException {
    if (id < -1) {
      throw new ComputerException("Computer id can't be null");
    }

    return id;
  }

  public String setValidName(String name) throws ComputerException {
    if (name == null) {
      throw new ComputerException("Computer must have a name");
    }
    return name;
  }

  public LocalDateTime setValideDate(Timestamp date) {
    if (date == null) {
      System.out.println("NULL");
      return null;
    }

    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

}
