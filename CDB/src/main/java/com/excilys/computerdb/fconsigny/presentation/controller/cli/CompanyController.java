package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.business.services.ICompanyServices;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CompanyController {

  private  IApp view;
  
  @Autowired
  private ICompanyServices companyServices;

  public CompanyController() {
  
  }
  
  public void setView(final IApp view){
    this.view = (UiViewComputer) view;
  }

  public void loadListCompany() {
    List<CompanyDto> companyList = null;
    try {
      companyList = this.companyServices.getAllCompanies();
    } catch (ServiceException exception) {
        this.view.showText(exception.toString());
    }
    
    if (companyList == null || companyList.isEmpty()) {
      this.view.showText("No company found");
    } else {
      for (CompanyDto company : companyList) {
        this.view.showText(company.toString());
      }
    }
  }

  public void loadCompanyById(final String strInput) {
    try {
      CompanyDto company = this.companyServices.getUniqueCompany(Integer.parseInt(strInput));
      this.view.showText(company.toString());
    } catch (NumberFormatException e) {
      this.view.showText("Wrong input command");
    } catch (ServiceException exception) {
      this.view.showText(exception.toString());
    }
  }
}
