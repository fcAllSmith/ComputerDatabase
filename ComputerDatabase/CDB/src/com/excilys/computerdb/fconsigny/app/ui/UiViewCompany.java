package com.excilys.computerdb.fconsigny.app.ui;

import java.io.IOException;

import com.excilys.computerdb.fconsigny.AppView;
import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.controller.CompanyController;

public class UiViewCompany extends AppView implements IApp {

  AppView appParentView ;
  private static CompanyController companyController;

  @Override
  public void createView(AppView appParentView) {
    if(this.appParentView == null && companyController == null){
      this.appParentView = appParentView ;
      companyController = new CompanyController(this);
    }
    refreshUi();
  }

  @Override
  public void refreshUi() {
    showText("------- Menu -------");
    showText("1 - Show list of companies") ;
    showText("2 - Show a company with an ID");
    String strInput;
    try {
      strInput = readInputText();
      onInputKey(strInput);
    } catch (IOException error) {
      error.printStackTrace();
    }	
  }
	
  public void onInputKey(String strInput) throws IOException {
    try {
      int cmd = Integer.parseInt(strInput);
      switch (cmd) {
        case 1 : 
          companyController.loadListCompany();
          break;
        case 2:
          showText("Company id : "); 
          String str_companId = readInputText();
          companyController.loadCompanyById(Integer.parseInt(str_companId));
          break;
        default : 
          destroyView();
          break;
      }
    } catch (NumberFormatException error) {
			showText("This input cannot be used. Use numbers instead :" + error);
    }
  }

  @Override
  public void destroyView() {
    UiViewCompany.companyController = null;
    ((IApp) appParentView).refreshUi();
  }
	
}