package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;

public class ComputerController {
	private static Logger logger = Logger.getLogger(ComputerController.class);

	private UiViewComputer view;
	public ComputerController(IApp view) {	
		this.view = (UiViewComputer)view;
	}

	public void loadListComputer(){
		try {
			List<ComputerDto> computerList = ComputerDtoMapper.transformListToDto(new ComputerServices().getAllComputers());
			if(computerList.isEmpty()){
				this.view.showText("No computer found");
			}else{
				for(ComputerDto computer : computerList){
					this.view.showText(computer.toString());
				}	
			}
		} catch (SQLException e) {
			this.view.showText("Database can't be reach");
			logger.error(e);
		}

	}

	public void loadComputerById(String strInputId){
		int id = Integer.parseInt(strInputId);

		try {
			ComputerDto computer = ComputerDtoMapper.transformToDto(new ComputerServices().getUniqueComputer(id));
			if(computer != null){
				this.view.showText(computer.toString());	
			}else{
				this.view.showText("No computer found");
			}
		} catch (SQLException e) {
			this.view.showText("Database can't be reach");
			logger.error(e);
		}
	}

	public void deleteComputer(String strInputId) throws NumberFormatException, SQLException{
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
	public void createComputer(String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(0);
		computerDto.setName("PC-TEST");
		computerDto.setIntroduced("2016-03-04");
		computerDto.setDiscontinued("2016-12-20");
		computerDto.setCompanyId(1);
		try {
			new ComputerServices().saveComputer(ComputerDtoMapper.transformToComputer(computerDto));
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public void updateComputer(String[] args){
		ComputerDto computerDto = new ComputerDto();
		computerDto.setId(0);
		computerDto.setName("PC-TEST");
		computerDto.setIntroduced("2016-03-04");
		computerDto.setDiscontinued("2016-12-20");
		computerDto.setCompanyId(1);

		try {
			new ComputerServices().editComptuter(ComputerDtoMapper.transformToComputer(computerDto));
		} catch (SQLException e) {
			logger.error(e);
		}
	}
}