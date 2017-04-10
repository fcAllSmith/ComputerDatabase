package src.main.java.com.excilys.computerdb.fconsigny.cli.controller;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import src.main.java.com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import src.main.java.com.excilys.computerdb.fconsigny.binding.mapper.ComputerDtoMapper;
import src.main.java.com.excilys.computerdb.fconsigny.service.services.IPageServices;
import src.main.java.com.excilys.computerdb.fconsigny.core.components.IPage;
import src.main.java.com.excilys.computerdb.fconsigny.cli.component.PageCli;
import src.main.java.com.excilys.computerdb.fconsigny.binding.dto.ComputerDto;
import src.main.java.com.excilys.computerdb.fconsigny.cli.view.IApp;
import src.main.java.com.excilys.computerdb.fconsigny.cli.component.UiViewComponents;
import src.main.java.com.excilys.computerdb.fconsigny.cli.view.UiViewPageComputer;

@Component
public class PageController {

  private PageCli pageCli;
  private  UiViewPageComputer view;

  @Autowired
  IPageServices pageServices; 

  public PageController() {
    pageCli = new PageCli(10);
  }

  public void setView(final IApp view){
    this.view = (UiViewPageComputer) view;
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
      computerDtoList = ComputerDtoMapper.transformListToDto(pageServices.getComputer(pageCli));

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
    st.append("       N          ");
    st.append(Integer.toString(pageCli.getCurrentPage()+1));
    this.view.showText(st.toString());
  }

}
