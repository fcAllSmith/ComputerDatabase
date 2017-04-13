package com.excilys.computerdb.fconsigny.cli.view;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdb.fconsigny.cli.controller.CompanyController;
import com.excilys.computerdb.fconsigny.cli.component.AppView;

@Component
public class UiViewCompany extends AppView implements IApp {

  private AppView appParentView;

  private static Logger logger = Logger.getLogger(UiViewCompany.class);

  @Autowired
  private CompanyController companyController;

  @Override
  public void createView(final IApp appParentView) {
    this.appParentView = (AppView) appParentView;
    companyController.setView(this);
    refreshUi();
  }

  @Override
  public void refreshUi() {
    showText("------- Menu -------");
    showText("1 - Show list of companies");
    showText("2 - Show a company with an ID");
    String strInput;
    try {
      strInput = readInputText();
      onInputKey(strInput);
    } catch (IOException error) {
      logger.error(error);
    }
  }

  public void onInputKey(final String strInput) throws IOException {
    try {
      int cmd = Integer.parseInt(strInput);
      switch (cmd) {
      case 1:
        this.companyController.loadListCompany();
        break;
      case 2:
        showText("Company id : ");
        this.companyController.loadCompanyById(readInputText());
        break;
      default:
        destroyView();
        break;
      }
    } catch (NumberFormatException error) {
      showText("This input cannot be used. Use numbers instead :" + error);
      logger.error(error);
    }
  }

  @Override
  public void destroyView() {
    this.companyController = null;
    ((IApp) appParentView).refreshUi();
  }

}
