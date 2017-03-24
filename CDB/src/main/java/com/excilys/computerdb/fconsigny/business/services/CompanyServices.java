package com.excilys.computerdb.fconsigny.business.services;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.factory.CompanyFactory;
import com.excilys.computerdb.fconsigny.business.mapper.CompanyDtoMapper;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;
import com.excilys.computerdb.fconsigny.storage.connection.datasource.IMysqlDatasource;
import com.excilys.computerdb.fconsigny.storage.dao.CompanyDao;
import com.excilys.computerdb.fconsigny.storage.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyServices")
public class CompanyServices implements ICompanyServices {

  private final CompanyDao companyDao = CompanyFactory.getCompanyDao() ;
      
  @Autowired
  IMysqlDatasource datasource;
  
  public CompanyServices(){
    System.out.println("Normal User Created->"+this);
  }

  public CompanyDto getUniqueCompany(final long id) throws ServiceException {
    CompanyDto companyDto = null; 
    try {
      companyDto =  CompanyDtoMapper.transformToDto(companyDao.findById(datasource.getConnection(),id));
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage());
    } finally {
      try {
        datasource.closeConnection(datasource.getConnection());
      } catch (SQLException | DatabaseException e) {
        throw new ServiceException (e.toString());
      }
    }
    return companyDto;
  }

  public List<CompanyDto> getAllCompanies() throws ServiceException {
    List<CompanyDto> companyDtoList = null; 
    try {
      companyDtoList =  CompanyDtoMapper.transformListToDto(companyDao.findAll(datasource.getConnection()));
    } catch (DatabaseException databaseException) {
      throw new ServiceException(databaseException.getMessage()); 
    } finally {
      try {
        datasource.closeConnection(datasource.getConnection());
      } catch (SQLException | DatabaseException e) {
        throw new ServiceException (e.toString());
      }
    }
    
    return companyDtoList;
  }
}
