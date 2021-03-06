package com.excilys.computerdb.fconsigny.binding.mapper;

import com.excilys.computerdb.fconsigny.core.model.Company;
import com.excilys.computerdb.fconsigny.core.model.Computer;
import com.excilys.computerdb.fconsigny.binding.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.binding.validator.ValidateEntries;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ComputerDtoMapper {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

  public static ComputerDto transformToDto(Computer computer) {
    if (computer != null) {
      ComputerDto computerDto = new ComputerDto();
      computerDto.setId(computer.getId());
      computerDto.setCompanyId(computer.getId());
      computerDto.setName(computer.getName());

      if (computer.getIntroduced() != null) {
        computerDto.setIntroduced(computer.getIntroduced().toLocalDate().toString());
      }

      if (computer.getDiscontinued() != null) {
        computerDto.setDiscontinued(computer.getDiscontinued().toLocalDate().toString());
      }

      if(computer.getCompany() != null){
        if (computer.getCompany().getName() != null) {
          computerDto.setCompanyName(computer.getCompany().getName());
        } 
      }

      return computerDto;
    }

    return null;
  }

  public static List<ComputerDto> transformListToDto(List<Computer> computerList) {
    List<ComputerDto> computerDtoList = new ArrayList<ComputerDto>();

    for (Computer computer : computerList) {
      computerDtoList.add(transformToDto(computer));
    }

    return computerDtoList;
  }

  public static Computer transformToComputer(ComputerDto computerDto) {

    Computer computer = new Computer((int)computerDto.getId());
    computer.setName(computerDto.getName());

    if (ValidateEntries.isDate(computerDto.getIntroduced())) {
      computer.setIntroduced(ValidateEntries.stringToLocalDate(computerDto.getIntroduced()));
    }

    if (ValidateEntries.isDate(computerDto.getDiscontinued())) {
      computer.setDiscontinued(ValidateEntries.stringToLocalDate(computerDto.getDiscontinued()));
    }

    computer.setCompany(new Company(computerDto.getCompanyId()));
    return computer;
  }
}
