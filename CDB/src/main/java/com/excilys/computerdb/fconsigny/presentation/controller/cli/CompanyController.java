package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.mapper.CompanyDtoMapper;
import com.excilys.computerdb.fconsigny.business.model.Company;
import com.excilys.computerdb.fconsigny.business.services.CompanyServices;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewCompany;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyController {

  private  IApp view;
  
  @Autowired
  private CompanyServices companyServices;

  public CompanyController() {}
  
  public void setView(final IApp view){
    this.view = (UiViewCompany) view;
  }

  public void loadListCompany() {
    List<Company> companyList = null;
    try {
      companyList = this.companyServices.getAllCompanies();
    } catch (ServiceException exception) {
        this.view.showText(exception.toString());
    }
    
    if (companyList == null || companyList.isEmpty()) {
      this.view.showText("No company found");
    } else {
      for (Company company : companyList) {
        CompanyDto companyDto = CompanyDtoMapper.transformToDto(company);
        this.view.showText(companyDto.toString());
      }
    }
  }

  public void loadCompanyById(final String strInput) {
    try {
      Company company = this.companyServices.getUniqueCompany(Integer.parseInt(strInput));
      CompanyDto companyDto = CompanyDtoMapper.transformToDto(company);
      this.view.showText(companyDto.toString());
    } catch (NumberFormatException e) {
      this.view.showText("Wrong input command");
    } catch (ServiceException exception) {
      this.view.showText(exception.toString());
    }
  }
}
