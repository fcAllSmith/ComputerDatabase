package com.excilys.computerdb.fconsigny.presentation.view.cli;

import org.springframework.context.ApplicationContext;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.PageController;
import com.excilys.computerdb.fconsigny.utils.view.AppView;

public class UiViewPageComputer extends AppView implements IApp {

  AppView appParentView;
  private PageController pageController;

  @Override
  public void createView(ApplicationContext context, final IApp appParentView) {
    this.appParentView = (AppView) appParentView;
    this.pageController = new PageController(context,this);
    pageController.loadListComputer();
    refreshUi();
  }

  @Override
  public void refreshUi() {
    
    String strInput = readInputText();
    onInputKey(strInput);
  }

  public void onInputKey(String strInput) {
    switch (strInput) {
    case "p":
      this.pageController.onPagePrevious();
      break;
    case "n":
      this.pageController.onPageNext();
      break;
    case "0":
      this.destroyView();
      break;
    }
    
    refreshUi();
  }

  @Override
  public void destroyView() {
    ((IApp) this.appParentView).refreshUi();
  }
}
