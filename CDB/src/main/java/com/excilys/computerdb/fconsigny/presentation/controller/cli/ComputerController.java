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
		computerDto.setName(args[0]);
		computerDto.setIntroduced(args[1]);
		computerDto.setDiscontinued(args[2]);
		computerDto.setCompanyId(Integer.parseInt(args[3]));

		new ComputerServices().saveComputer(ComputerDtoMapper.transformToComputer(computerDto));

	}

	public void updateComputer(final String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(Integer.parseInt(args[0]));
		computerDto.setName(args[1]);
		computerDto.setIntroduced(args[2]);
		computerDto.setDiscontinued(args[3]);
		computerDto.setCompanyId(Integer.parseInt(args[4]));

		new ComputerServices().editComptuter(ComputerDtoMapper.transformToComputer(computerDto));
	}
}
