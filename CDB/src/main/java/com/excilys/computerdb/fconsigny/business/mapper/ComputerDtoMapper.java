package com.excilys.computerdb.fconsigny.business.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

public class ComputerDtoMapper {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

	public static ComputerDto transformToDto(Computer computer){
		if(computer != null){
			ComputerDto computerDto = new ComputerDto();
			computerDto.setId(computer.getId());
			computerDto.setCompanyId(computer.getId());
			computerDto.setName(computer.getName());

			if (computer.getIntroduced()!= null) {
				computerDto.setIntroduced(computer.getIntroduced().toString());
			}

			if (computer.getDiscontinued()!= null) {
			//	computerDto.setDiscontinued(computer.getDiscontinued().toString());
			}

			computerDto.setCompanyId(computer.getCompanyId());
			return computerDto;
		}

		return null;
	}

	public static List<ComputerDto> transformListToDto(List<Computer> computerList){
		List<ComputerDto> computerDtoList = new ArrayList<ComputerDto>();;

		for(Computer computer : computerList){
			computerDtoList.add(transformToDto(computer));
		}

		return computerDtoList;
	}

	public static Computer transformToComputer(ComputerDto computerDto){

		Computer computer  = new Computer(computerDto.getId());
		computer.setName(computerDto.getName());

		//if(!computerDto.getInserted().matches("-") && computerDto.getInserted() != null){
			//LocalDateTime dateTime = LocalDateTime.parse(computerDto.getInserted(),formatter);
		//}

		//if(!computerDto.getDiscontinued().matches("-") && computerDto.getDiscontinued() != null){

		//}

		computer.setCompanyId(computerDto.getCompanyId());
		return computer;

	}
}