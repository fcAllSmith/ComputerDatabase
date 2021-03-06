package com.excilys.computerdb.fconsigny.binding.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.computerdb.fconsigny.binding.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.binding.exceptions.ComputerArgumentFormatException;

public class ValidateEntries {

  public static void verifyEntries(ComputerDto computerDto) throws ComputerArgumentFormatException {
    if (computerDto.getCompanyName() == null || computerDto.getCompanyName().isEmpty()) {
      throw new ComputerArgumentFormatException("Computer name can't be null");
    }

    if (computerDto.getCompanyId() < 0) {
      throw new ComputerArgumentFormatException("Company Id can't be null");
    }

    if (computerDto.getIntroduced() != null) {
      if (stringToLocalDate(computerDto.getIntroduced()) == null) {
        throw new ComputerArgumentFormatException("Introduce date wrong format please respect the pattern YYYY/MM/dd");
      }

      if (computerDto.getDiscontinued() != null) {
        if (stringToLocalDate(computerDto.getDiscontinued()) == null) {
          throw new ComputerArgumentFormatException(
              "Discontinued date wrong format please respect the pattern YYYY/MM/dd");
        }
      }
    }
  }

  public static LocalDateTime stringToLocalDate(String inputDate) {
    if (inputDate == null) {
      return null;
    }

    try {
      DateTimeFormatter formatter;
      LocalDate date;
      formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      date = LocalDate.parse(inputDate, formatter);
      return date.atStartOfDay();
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  public static boolean isValideId(long inputId) {
    if (inputId > -1) {
      return true;
    }

    return false;
  }

  public static boolean isDate(String inputDate) {
    if (inputDate != null) {
      if (!inputDate.matches("-")) {
        return true;
      }
    }

    return false;
  }

}
