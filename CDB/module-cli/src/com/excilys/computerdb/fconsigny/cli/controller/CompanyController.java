package com.excilys.computerdb.fconsigny.cli.controller;

import com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import com.excilys.computerdb.fconsigny.binding.mapper.CompanyDtoMapper;
import com.excilys.computerdb.fconsigny.core.model.Company;

import com.excilys.computerdb.fconsigny.service.services.ICompanyServices;
import com.excilys.computerdb.fconsigny.binding.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.cli.view.IApp;
import com.excilys.computerdb.fconsigny.cli.view.UiViewCompany;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyController {

  private  UiViewCompany view;
 
  @Autowired
  private ICompanyServices companyServices;

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
