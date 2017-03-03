package com.excilys.computerdb.fconsigny.presentation.controller;

import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;

import java.util.List;

public class CompanyController {

  private IApp view;
  public CompanyController(IApp view) {
    this.view = view;
  }

  public void loadListCompany() {
    List<CompanyDto> companyList = new CompanyServices().getAllCompanies();
    if(companyList.isEmpty()){
     this.view.showText("No company found");
    }else{
      for(CompanyDto company : companyList){
        this.view.showText(company.toString());
      }	
    }
  }

  public void loadCompanyById(String strInput) {
    try {
      Company company = new CompanyServices().getUniqueCompany(Integer.parseInt(strInput));
      this.view.showText(company.toString());;
    } catch (CompanyException error) {
      error.printStackTrace();
      this.view.showText("No result found");
    }
  }
}
