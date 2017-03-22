package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.exception.ComputerArgumentFormatException;
import com.excilys.computerdb.fconsigny.presentation.validation.ValidateEntries;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;
import com.excilys.computerdb.fconsigny.spring.ApplicationConfig;
import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComputerController {
  private static Logger logger = Logger.getLogger(ComputerController.class);

  private final UiViewComputer view;

  @Autowired
  private ApplicationContext context;

  public ComputerController(final IApp view) {
    this.view = (UiViewComputer) view;
    this.context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  }

  /**
   * Call the computer service, Map the computer list into a computerDtoList.
   */
  public void loadListComputerDto() {
    List<ComputerDto> computerDtoList = null;
    try {
      computerDtoList = ComputerDtoMapper
          .transformListToDto(this.context.getBean(IComputerServices.class).getAllComputers());
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
      computerDto = ComputerDtoMapper
          .transformToDto(this.context.getBean(IComputerServices.class).getUniqueComputer(id));
    } catch (BeansException | ServiceException exception) {
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
      if (this.context.getBean(IComputerServices.class).deleteComputer(Integer.parseInt(strInputId))) {
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
      ComputerDto computerDto = fillComputerDto(args);
      ValidateEntries.verifyEntries(computerDto);
      try {
        if (this.context.getBean(IComputerServices.class)
            .editComptuter(ComputerDtoMapper.transformToComputer(computerDto))) {
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
        if (this.context.getBean(IComputerServices.class)
            .editComptuter(ComputerDtoMapper.transformToComputer(computerDto))) {
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
