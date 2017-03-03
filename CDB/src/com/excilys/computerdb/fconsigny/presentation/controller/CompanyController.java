package com.excilys.computerdb.fconsigny.presentation.controller;

import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewCompany;
import com.excilys.computerdb.fconsigny.storage.exceptions.CompanyException;
import com.excilys.computerdb.fconsigny.utils.view.AppView;

import java.util.List;

public class CompanyController {

  private IApp view;
  public CompanyController(IApp view) {
    this.view = view;
  }

  public void loadListCompany() {
    List<Company> companyList = new CompanyServices().getAllCompanies();
    if(companyList.isEmpty()){
      ((UiViewCompany)view).showText("No company found");
    }else{
      for(Company company : companyList){
        ((UiViewCompany)view).showText(company.toString());
      }	
    }
  }

  public void loadCompanyById(int id) {
    try {
      Company company = new CompanyServices().getUniqueCompany(id);
      ((UiViewCompany)view).showText(company.toString());;
    } catch (CompanyException error) {
      error.printStackTrace();
      ((AppView) view).showText("No result found");
    }
  }
}
