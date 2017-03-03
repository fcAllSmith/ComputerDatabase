package com.excilys.computerdb.fconsigny.business.mapper;

import java.util.Collections;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

public class ComputerDtoMapper {

	public static ComputerDto transformToDto(Computer computer){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setCompanyId(computer.getId());
		computerDto.setName(computer.getName());
		
		if (computer.getIntroduced()!= null) {
			computerDto.setInserted(computer.getIntroduced().toString());
		}
		
		if (computer.getIntroduced()!= null) {
			computerDto.setDiscontinued(computer.getDiscontinued().toString());
		}

		computerDto.setCompanyId(computer.getCompanyId());
		return computerDto;
	}
	
	public static List<ComputerDto> transformListToDto(List<Computer> computerList){
		List<ComputerDto> computerDtoList = Collections.emptyList();
		
		for(Computer computer : computerList){
			computerDtoList.add(transformToDto(computer));
		}
		
		return computerDtoList;
	}
}
