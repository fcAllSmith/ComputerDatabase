package src.main.java.com.excilys.computerdb.fconsigny.service.services;

import java.util.List;

import src.main.java.com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import src.main.java.com.excilys.computerdb.fconsigny.core.model.Computer;

import com.excilys.computerdb.fconsigny.presentation.component.IPage;

public interface IPageServices {
  
  public List<Computer> getComputer(IPage page) throws ServiceException;
  
  public List<Computer> getComputerFilterCompany(IPage page) throws ServiceException;
  
}
