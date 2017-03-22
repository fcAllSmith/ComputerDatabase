package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IPageServices;
import com.excilys.computerdb.fconsigny.presentation.component.PageCli;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewPageComputer;
import com.excilys.computerdb.fconsigny.spring.ApplicationConfig;

public class PageController {

  private PageCli pageCli;

  @Autowired
  private ApplicationContext context;

  private final UiViewPageComputer view;

  public PageController(IApp view) {
    this.view = (UiViewPageComputer) view;
    this.context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    pageCli = new PageCli(10);
  }

  public void onPageNext() {
    if (pageCli.nextPage()) {
      loadListComputer();
    } else {
      this.view.showText("Page limit, cannot go further");
    }
  }

  public void onPagePrevious() {
    if (pageCli.previousPage()) {
      loadListComputer();
    } else {
      this.view.showText(" This is already the first page");
    }
  }

  public void loadListComputer() {
    List<ComputerDto> computerDtoList = null;
    try {
      computerDtoList = ComputerDtoMapper
          .transformListToDto(this.context.getBean(IPageServices.class).getComputer(pageCli));
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

}
