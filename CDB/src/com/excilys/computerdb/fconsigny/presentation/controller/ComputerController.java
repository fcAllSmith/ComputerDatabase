package com.excilys.computerdb.fconsigny.presentation.controller;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewCompany;
import com.excilys.computerdb.fconsigny.storage.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.storage.mapper.MysqlComputerMapper;

public class ComputerController {

	private IApp view;
	public ComputerController(IApp view) {	
		this.view = view;
	}

	public void loadListComputer(){
		List<Computer> computerList  = new ComputerServices().getAllComputers();
		if(computerList.isEmpty()){
			((UiViewCompany)view).showText("No computer found");
		}else{
			for(Computer computer : computerList){
				((UiViewCompany)view).showText(computer.toString());
			}	
		}
	}

	public void loadComputerById(String strInputId){
		int id = Integer.parseInt(strInputId);

		try {
			Computer computer = new ComputerServices().getUniqueComputer(id);
			((UiViewCompany)view).showText(computer.toString());
		} catch (ComputerException e) {
			e.printStackTrace();
			((UiViewCompany)view).showText("No computer found");
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
		Computer computer = MysqlComputerMapper.stringIntoComputer(args);
	}
}
