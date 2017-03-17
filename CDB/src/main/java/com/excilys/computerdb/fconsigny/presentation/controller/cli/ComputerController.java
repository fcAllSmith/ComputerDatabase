package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;


public class ComputerController {
	private static Logger logger = Logger.getLogger(ComputerController.class);

	private final UiViewComputer view;
	public ComputerController(final IApp view) {	
		this.view = (UiViewComputer)view;
	}

	public void loadListComputer(){

		List<ComputerDto> computerList = ComputerDtoMapper.transformListToDto(new ComputerServices().getAllComputers());
		if(computerList.isEmpty()){
			this.view.showText("No computer found");
		}else{
			for(ComputerDto computer : computerList){
				this.view.showText(computer.toString());
			}	
		}
	}

	public void loadComputerById(final String strInputId){
		int id = Integer.parseInt(strInputId);

		ComputerDto computer = ComputerDtoMapper.transformToDto(new ComputerServices().getUniqueComputer(id));
		if(computer != null){
			this.view.showText(computer.toString());	
		}else{
			this.view.showText("No computer found");
		}

	}

	public void deleteComputer(final String strInputId) throws NumberFormatException{
		new ComputerServices().deleteComputer(Integer.parseInt(strInputId));
	}

	/**
	 * 
	 * @param args 
	 * [0] name
	 * [1] introduced
	 * [2] discontinued
	 * [3] companyId
	 */
	public void createComputer(final String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(0);
		computerDto.setName("PC-TEST");
		computerDto.setIntroduced("2016-03-04");
		computerDto.setDiscontinued("2016-12-20");
		computerDto.setCompanyId(1);

		new ComputerServices().saveComputer(ComputerDtoMapper.transformToComputer(computerDto));

	}

	public void updateComputer(final String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(0);
		computerDto.setName("PC-TEST");
		computerDto.setIntroduced("2016-03-04");
		computerDto.setDiscontinued("2016-12-20");
		computerDto.setCompanyId(1);

		new ComputerServices().editComptuter(ComputerDtoMapper.transformToComputer(computerDto));
	}
}
