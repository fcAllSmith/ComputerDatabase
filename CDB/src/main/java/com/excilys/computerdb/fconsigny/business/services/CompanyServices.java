package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.CompanyFactory;
import com.excilys.computerdb.fconsigny.business.mapper.CompanyDtoMapper;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServices implements ICompanyServices {

  private final CompanyDao cDao = CompanyFactory.getCompanyDao();

  @Autowired
  IMysqlDatasource datasource;

  @Autowired
  public CompanyServices(){}

  public CompanyDto getUniqueCompany(final long id) throws ServiceException {
    try {
      return CompanyDtoMapper.transformToDto(cDao.findById(datasource.getConnection(),id));
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    }
  }

  public List<CompanyDto> getAllCompanies() throws ServiceException{
    try {
      return CompanyDtoMapper.transformListToDto(cDao.findAll(datasource.getConnection()));
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage()); 
    }
  }
}
