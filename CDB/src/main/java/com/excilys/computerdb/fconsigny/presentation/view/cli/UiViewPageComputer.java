package com.excilys.computerdb.fconsigny.presentation.view.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.PageController;
import com.excilys.computerdb.fconsigny.utils.view.AppView;

@Component
public class UiViewPageComputer extends AppView implements IApp {

  AppView appParentView;
  
  @Autowired
  private PageController pageController;

  @Override
  public void createView(final IApp appParentView) {
    this.appParentView = (AppView) appParentView;
    pageController.setView(this);
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
