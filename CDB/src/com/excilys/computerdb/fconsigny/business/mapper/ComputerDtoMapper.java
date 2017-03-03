package com.excilys.computerdb.fconsigny.business.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

public class ComputerDtoMapper {

	public static ComputerDto transformToDto(Computer computer){
		if(computer != null){
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
		
		return null;
	}

	public static List<ComputerDto> transformListToDto(List<Computer> computerList){
		List<ComputerDto> computerDtoList = new ArrayList<ComputerDto>();;

		for(Computer computer : computerList){
			computerDtoList.add(transformToDto(computer));
		}

		return computerDtoList;
	}
}
