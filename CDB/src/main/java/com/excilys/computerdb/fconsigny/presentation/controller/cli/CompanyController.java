package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;

import java.sql.SQLException;
import java.util.List;

public class CompanyController {

	private IApp view;
	public CompanyController(IApp view) {
		this.view = view;
	}

	public void loadListCompany() {
		List<CompanyDto> companyList;

		try {
			companyList = new CompanyServices().getAllCompanies();
			if(companyList.isEmpty()){
				this.view.showText("No company found");
			}else{
				for(CompanyDto company : companyList){
					this.view.showText(company.toString());
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void loadCompanyById(String strInput) {
		try {
			CompanyDto company = new CompanyServices().getUniqueCompany(Integer.parseInt(strInput));
			this.view.showText(company.toString());;

		} catch (NumberFormatException e) {
			this.view.showText("Wrong input command");
			e.printStackTrace();
		} catch (SQLException e) {
			this.view.showText("Database can't be reach or empty result");
			e.printStackTrace();
		}
	}
}