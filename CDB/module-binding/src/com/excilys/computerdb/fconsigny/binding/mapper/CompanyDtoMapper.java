package com.excilys.computerdb.fconsigny.binding.mapper;

import com.excilys.computerdb.fconsigny.core.model.Company;
import com.excilys.computerdb.fconsigny.binding.dto.CompanyDto;

import java.util.ArrayList;
import java.util.List;

public class CompanyDtoMapper {

  public static CompanyDto transformToDto(Company company) {
    CompanyDto companyDto = null;

    if (company.getName() != null) {
      companyDto = new CompanyDto();
      companyDto.setId(company.getId());
      companyDto.setName(company.getName());
    }

    return companyDto;
  }

  public static List<CompanyDto> transformListToDto(List<Company> companyList) {
    List<CompanyDto> companyDtoList = new ArrayList<CompanyDto>();
    for (Company company : companyList) {
      companyDtoList.add(transformToDto(company));
    }

    return companyDtoList;
  }
}
