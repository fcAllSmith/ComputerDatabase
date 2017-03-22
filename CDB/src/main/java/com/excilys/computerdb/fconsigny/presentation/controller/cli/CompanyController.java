package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;

import java.sql.SQLException;
import java.util.List;

public class CompanyController {

  private final IApp view;
  private CompanyServices companyServices;

  public CompanyController(final IApp view) {
    this.view = view;
    this.companyServices = new CompanyServices();
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
