package src.main.java.com.excilys.computerdb.fconsigny.cli.controller;

import src.main.java.com.excilys.computerdb.fconsigny.cli.dto.ComputerDto;
import src.main.java.com.excilys.computerdb.fconsigny.cli.exception.ComputerArgumentFormatException;

import src.main.java.com.excilys.computerdb.fconsigny.cli.view.IApp;
import src.main.java.com.excilys.computerdb.fconsigny.cli.view.UiViewComputer;
import src.main.java.com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import src.main.java.com.excilys.computerdb.fconsigny.service.services.IComputerServices;

import src.main.java.com.excilys.computerdb.fconsigny.binding.mapper.ComputerDtoMapper;
import src.main.java.com.excilys.computerdb.fconsigny.core.model.Computer;

import src.main.java.com.excilys.computerdb.fconsigny.cli.validation.ValidateEntries;

import org.springframework.beans.BeansException;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ComputerController {

  private UiViewComputer view;

  @Autowired
  IComputerServices computerServices; 

  public ComputerController() {}

  public void setView(final IApp view){
    this.view = (UiViewComputer) view;
  }

  /**
   * Call the computer service, Map the computer list into a computerDtoList.
   */
  public void loadListComputerDto() {
    List<ComputerDto> computerDtoList = null;
    try {
      computerDtoList = ComputerDtoMapper.transformListToDto(computerServices.getAllComputers());
    } catch (BeansException | ServiceException exception) {
      this.view.showText(exception.toString());
    } 

    if (computerDtoList == null || computerDtoList.isEmpty()) {
      this.view.showText("No computer found");
    } else {
      for (ComputerDto computerDto : computerDtoList) {
        this.view.showText(computerDto.toString());
      }
    }
  }

  /**
   * Call the computer service in order to get one computer. Map the computer
   * into a computerDto. Show the computerDto if it's not empty.
   * 
   * @param strInputId
   *          : the id in string.
   */
  public void loadDtoComputerById(final String strInputId) {
    int id = Integer.parseInt(strInputId);
    ComputerDto computerDto = null;
    
      try {
        Computer  computer = computerServices.getUniqueComputer(id);
        computerDto = ComputerDtoMapper.transformToDto(computer);
      } catch ( Exception exception) {
        this.view.showText(exception.toString());
      }
    
    if (computerDto != null) {
      this.view.showText(computerDto.toString());
    } else {
      this.view.showText("No computer found");
    }
  }

  public void deleteComputer(final String strInputId) {
    try {
      if (computerServices.deleteComputer(Integer.parseInt(strInputId))) {
        this.view.showText("The computer has been removed");
      } else {
        this.view.showText("Enable to delete the computer with ID :" + strInputId);
      }
    } catch (BeansException | ServiceException exception) {
      this.view.showText(exception.toString());
    } catch (NumberFormatException numberFormatException) {
      this.view.showText(numberFormatException.toString());
    } 
  }

  /**
   * @param args
   *          : entries following the order below [0] name [1] introduced [2]
   *          discontinued [3] companyId .
   */
  public void createComputer(final String[] args) {

    try {
      ComputerDto computerDto = new ComputerDto();
      computerDto.setName(args[0]);
      computerDto.setIntroduced(args[1]);
      computerDto.setDiscontinued(args[2]);
      computerDto.setCompanyId(Integer.parseInt(args[3]));     
      ValidateEntries.verifyEntries(computerDto);
      try {
        if (computerServices.editComptuter(ComputerDtoMapper.transformToComputer(computerDto))) {
          this.view.showText("The computer has been added");
        } else {
          this.view.showText("Enable to save the computer");
        }
      } catch (BeansException | ServiceException exception ) {
        this.view.showText(exception.toString());
      }
    } catch (ComputerArgumentFormatException castException) {
      this.view.showText(castException.toString());
    }
  }

  /**
   * Validate entries. If they are correct. Try to record it.
   * 
   * @param args
   *          : entries following the order below [0] name [1] introduced [2]
   *          discontinued [3] companyId .
   */
  public void updateComputer(final String[] args) {

    try {
      ComputerDto computerDto = fillComputerDto(args);
      ValidateEntries.verifyEntries(computerDto);
      try {
        if (computerServices.editComptuter(ComputerDtoMapper.transformToComputer(computerDto))) {
          this.view.showText("The computer has been updated");
        } else {
          this.view.showText("Enable to update the computer");
        }
      } catch (BeansException |ServiceException exception) {
        this.view.showText(exception.toString());      }
    } catch (ComputerArgumentFormatException castException) {
      this.view.showText(castException.toString());
    }
  }

  public ComputerDto fillComputerDto(final String[] args) {
    ComputerDto computerDto = new ComputerDto();
    computerDto.setId(Integer.parseInt(args[0]));
    computerDto.setName(args[1]);
    computerDto.setIntroduced(args[2]);
    computerDto.setDiscontinued(args[3]);
    computerDto.setCompanyId(Integer.parseInt(args[4]));
    return computerDto;
  }
}
