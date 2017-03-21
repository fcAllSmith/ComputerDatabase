package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import org.springframework.beans.factory.annotation.Autowired;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class ComputerController {
	private static Logger logger = Logger.getLogger(ComputerController.class);

	private final UiViewComputer view;

	@Autowired
	ApplicationContext context; 

	public ComputerController(final IApp view) {	
		this.view = (UiViewComputer) view; 
	}

	public void loadListComputer(){
 
		List<ComputerDto> computerDtoList = ComputerDtoMapper.transformListToDto(this.context.getBean(IComputerServices.class).getAllComputers());
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

		ComputerDto computerDto = ComputerDtoMapper.transformToDto(this.context.getBean(IComputerServices.class).getUniqueComputer(id));
		if(computerDto != null){
			this.view.showText(computerDto.toString());	
		}else{
			this.view.showText("No computer found");
		}
	}

	public void deleteComputer(final String strInputId) throws NumberFormatException{
		this.context.getBean(IComputerServices.class).deleteComputer(Integer.parseInt(strInputId));
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

		this.context.getBean(IComputerServices.class).saveComputer(ComputerDtoMapper.transformToComputer(computerDto));

	}

	public void updateComputer(final String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(Integer.parseInt(args[0]));
		computerDto.setName(args[1]);
		computerDto.setIntroduced(args[2]);
		computerDto.setDiscontinued(args[3]);
		computerDto.setCompanyId(Integer.parseInt(args[4]));

		this.context.getBean(IComputerServices.class).editComptuter(ComputerDtoMapper.transformToComputer(computerDto));
	}
}
