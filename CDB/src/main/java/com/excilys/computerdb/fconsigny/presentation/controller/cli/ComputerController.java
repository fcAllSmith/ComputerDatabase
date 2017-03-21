package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;

import java.util.List;
import org.apache.log4j.Logger;

public class ComputerController {
	private static Logger logger = Logger.getLogger(ComputerController.class);

	private final UiViewComputer view;
	private IComputerServices computerServices = null;; 

	public ComputerController(final IApp view) {	
		this.view = (UiViewComputer) view;
		this.computerServices = new ComputerServices();
	}

	public void loadListComputer(){

		List<ComputerDto> computerDtoList = ComputerDtoMapper.transformListToDto(this.computerServices.getAllComputers());
		if(computerDtoList == null || computerDtoList.isEmpty()){
			this.view.showText("No computer found");
		}else{
			for(ComputerDto computerDto : computerDtoList){
				this.view.showText(computerDto.toString());
			}	
		}
	}

	public void loadComputerById(final String strInputId){
		int id = Integer.parseInt(strInputId);

		ComputerDto computerDto = ComputerDtoMapper.transformToDto(this.computerServices.getUniqueComputer(id));
		if(computerDto != null){
			this.view.showText(computerDto.toString());	
		}else{
			this.view.showText("No computer found");
		}
	}

	public void deleteComputer(final String strInputId) throws NumberFormatException{
		this.computerServices.deleteComputer(Integer.parseInt(strInputId));
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

		this.computerServices.saveComputer(ComputerDtoMapper.transformToComputer(computerDto));

	}

	public void updateComputer(final String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(Integer.parseInt(args[0]));
		computerDto.setName(args[1]);
		computerDto.setIntroduced(args[2]);
		computerDto.setDiscontinued(args[3]);
		computerDto.setCompanyId(Integer.parseInt(args[4]));

		this.computerServices.editComptuter(ComputerDtoMapper.transformToComputer(computerDto));
	}
}
