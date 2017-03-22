package com.excilys.computerdb.fconsigny.presentation.view.cli;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.PageController;
import com.excilys.computerdb.fconsigny.utils.view.AppView;

public class UiViewPageComputer extends AppView implements IApp {

  AppView appParentView;
  private PageController pageController;

  @Override
  public void createView(final AppView appParentView) {
    this.appParentView = appParentView;
    this.pageController = new PageController(this);
    
    refreshUi();
  }

  @Override
  public void refreshUi() {
    pageController.loadListComputer();
    showText(UiViewComponents.TOP_BORDER);
    showText("P             -              N");
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
