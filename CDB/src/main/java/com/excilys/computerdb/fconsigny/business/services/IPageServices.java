package com.excilys.computerdb.fconsigny.business.services;

import java.util.List;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.model.Computer;
import com.excilys.computerdb.fconsigny.presentation.component.IPage;

public interface IPageServices {
  
  public List<Computer> getComputer(IPage page) throws ServiceException;
  
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException;
  
}
