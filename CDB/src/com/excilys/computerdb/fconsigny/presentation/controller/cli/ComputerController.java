package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;

import com.excilys.computerdb.fconsigny.business.services.ComputerServices;

public class ComputerController {

	private UiViewComputer view;
	public ComputerController(IApp view) {	
		this.view = (UiViewComputer)view;
	}

	public void loadListComputer(){
		try {
			List<ComputerDto> computerList = new ComputerServices().getAllComputers();
			if(computerList.isEmpty()){
				this.view.showText("No computer found");
			}else{
				for(ComputerDto computer : computerList){
					this.view.showText(computer.toString());
				}	
			}
		} catch (SQLException e) {
			this.view.showText("Database can't be reach");
			e.printStackTrace();
		}
		
	}

	public void loadComputerById(String strInputId){
		int id = Integer.parseInt(strInputId);

		try {
			ComputerDto computer = new ComputerServices().getUniqueComputer(id);
			if(computer != null){
				this.view.showText(computer.toString());	
			}else{
				this.view.showText("No computer found");
			}
		} catch (SQLException e) {
			this.view.showText("Database can't be reach");
			e.printStackTrace();
		}
		
	}

	public void deleteComputer(String strInputId){

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
		//ComputerDto computer = MysqlComputerMapper.stringIntoComputer(args);
	}
}
