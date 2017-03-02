package com.excilys.computerdb.fconsigny.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.ui.UiViewCompany;
import com.excilys.computerdb.fconsigny.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.mapper.ComputerMapper;
import com.excilys.computerdb.fconsigny.model.Computer;
import com.excilys.computerdb.fconsigny.services.ComputerServices;

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
		Computer computer = ComputerMapper.stringIntoComputer(args);
	}
}
