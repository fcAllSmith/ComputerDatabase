package com.excilys.computerdb.fconsigny.app.ui;

import java.io.IOException;

import com.excilys.computerdb.fconsigny.AppView;
import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.controller.ComputerController;

public class UiViewComputer extends AppView implements IApp {

  AppView appParentView ;
  private  ComputerController computerController;

  @Override
  public void createView(AppView appParentView) {
    if (this.appParentView == null && computerController == null) {
      this.appParentView = appParentView ;
      computerController = new ComputerController(this);
    }
    refreshUi();	
  }

  @Override
  public void refreshUi() {
    showText("test -- menu");
    showText("1 - Show list of computers") ;
    showText("2 - Select a computer for info") ;
    showText("3 - Remove a computer from the data base ");
    showText("4 - Create a computer ");
    showText("5 - Update a computer");
    String strInput;
    try {
      strInput = readInputText();
      onInputKey(strInput);
    } catch (IOException error) {
    	error.printStackTrace();
    }
  }

  @Override
  public void destroyView() {
    ((IApp) appParentView).refreshUi();
  }

  public void onInputKey(String strInput) throws IOException {
    int cmd = Integer.parseInt(strInput);
    switch (cmd) {
      case 0: 
        refreshUi();
        break;
      case 1:
        computerController.loadListComputer();
        break ;
      case 2:
        showText("Computer id  : ");
        computerController.loadComputerById(readInputText());
        break;
      case 3:
        showText("Computer id : ");
        computerController.deleteComputer(readInputText());
        break;
      default:
        this.destroyView();
        break;
    }
    refreshUi();
  }
}
