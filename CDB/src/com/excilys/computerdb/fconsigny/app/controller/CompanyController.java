package com.excilys.computerdb.fconsigny.app.controller;

import java.util.List;

import com.excilys.computerdb.fconsigny.AppView;
import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.ui.UiViewCompany;
import com.excilys.computerdb.fconsigny.exceptions.CompanyException;
import com.excilys.computerdb.fconsigny.model.Company;
import com.excilys.computerdb.fconsigny.services.CompanyServices;

public class CompanyController {

	private IApp view;
	public CompanyController(IApp view) {
		this.view = view;
	}

	public void loadListCompany(){
		List<Company> companyList = new CompanyServices().getAllCompanies();
		for(Company company : companyList){
			((UiViewCompany)view).showText(company.toString());
		}
	}

	public void loadCompanyById(int id){
		try {
			Company company = new CompanyServices().getUniqueCompany(id);
			((UiViewCompany)view).showText(company.toString());;

		} catch (CompanyException e) {
			e.printStackTrace();
			((AppView) view).showText("No result found");
		}
	}


}
