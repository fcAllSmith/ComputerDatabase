package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.IPageServices;
import com.excilys.computerdb.fconsigny.business.services.PageServices;
import com.excilys.computerdb.fconsigny.presentation.component.PageCli;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComponents;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewPageComputer;
import com.excilys.computerdb.fconsigny.spring.ApplicationConfig;

public class PageController {

  private PageCli pageCli;
  private final UiViewPageComputer view;
  
  @Autowired
  IPageServices pageServices; 

  public PageController(ApplicationContext context, IApp view) {
    this.view = (UiViewPageComputer) view;
    pageServices = (IPageServices) context.getBean("pageService");
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
          .transformListToDto(pageServices.getComputer(pageCli));
      
    } catch (BeansException | ServiceException exception) {
      this.view.showText(exception.toString());
    }

    if (computerDtoList == null || computerDtoList.isEmpty()) {
      this.view.showText("No computer found");
    } else {
      for (ComputerDto computerDto : computerDtoList) {
        this.view.showText(computerDto.toString());
      }
      this.view.showText(UiViewComponents.BOTTOM_BORDER);
      setBottomPageFooter();
      this.view.showText(UiViewComponents.BOTTOM_BORDER);
    }
    
  }
  
  public void setBottomPageFooter(){
    StringBuilder st = new StringBuilder(); 
    st.append("       P        "); 
    st.append(Integer.toString(pageCli.getCurrentPage()-1));
    st.append("               ");
    st.append(Integer.toString(pageCli.getCurrentPage()));
    st.append("     N          ");
    st.append(Integer.toString(pageCli.getCurrentPage()+1));
    this.view.showText(st.toString());
  }

}
