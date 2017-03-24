package com.excilys.computerdb.fconsigny.presentation.view.cli;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.ComputerController;
import com.excilys.computerdb.fconsigny.utils.view.AppView;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UiViewComputer extends AppView implements IApp {

  private static Logger logger = Logger.getLogger(UiViewComputer.class);

  private AppView appParentView;

  @Autowired
  private ComputerController computerController;

  @Override
  public void createView(final IApp appParentView) {

    this.appParentView = (AppView )appParentView;
    computerController.setView(this);
    refreshUi();
  }

  @Override
  public void refreshUi() {
    showText("test -- menu");
    showText("1 - Show list of computers");
    showText("2 - Select a computer for info");
    showText("3 - Remove a computer from the data base ");
    showText("4 - Create a computer ");
    showText("5 - Update a computer");
    String strInput;
    try {
      strInput = readInputText();
      onInputKey(strInput);
    } catch (IOException error) {
      logger.error(error);
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
      computerController.loadListComputerDto();
      break;
    case 2:
      showText("Computer id  : ");
      computerController.loadDtoComputerById(readInputText());
      break;
    case 3:
      showText("Computer id : ");
      try {
        computerController.deleteComputer(readInputText());
      } catch (NumberFormatException e) {
        logger.error(e);
      }
      break;
    case 4:
      String[] args = new String[4];
      showText("Name : ");
      args[0] = readInputText();
      showText("Introduced : ");
      args[1] = readInputText();
      showText("Discontinued : ");
      args[2] = readInputText();
      showText("Company Id : ");
      args[3] = readInputText();
      computerController.createComputer(args);
      break;
    case 5:
      String[] args_update = new String[5];
      showText("Id:");
      args_update[0] = readInputText();
      showText("Name : ");
      args_update[1] = readInputText();
      showText("Introduced : ");
      args_update[2] = readInputText();
      showText("Discontinued : ");
      args_update[3] = readInputText();
      showText("Company Id : ");
      args_update[4] = readInputText();
      computerController.updateComputer(args_update);
      break;
    default:
      this.destroyView();
      break;
    }

    refreshUi();
  }
}
