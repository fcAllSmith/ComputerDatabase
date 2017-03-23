package com.excilys.computerdb.fconsigny.presentation.view.cli;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.excilys.computerdb.fconsigny.utils.view.AppView;

public class UiViewLauncher extends AppView implements IApp {

  private static Logger logger = Logger.getLogger(UiViewLauncher.class);

  @Override
  public void createView(final IApp appParentView) {
    refreshUi();
  }

  @Override
  public void refreshUi() {
    showText("------- Menu -------");
    showText("1 - Computers");
    showText("2 - Companies");
    showText("3 - Dashboard");
    showText("What do you want to do ? :");

    onInputKey(readInputText());
  }

  @Override
  public void destroyView() {

  }

  public void onInputKey(final String strInput) {
    try {
      int cmd = Integer.parseInt(strInput);
      switch (cmd) {
      case 1:
        startView(new UiViewComputer());
        break;
      case 2:
        startView(new UiViewCompany());
        break;
      case 3:
        startView(new UiViewPageComputer());
        break;
      default:
        showText("command not found");
        break;
      }
    } catch (NumberFormatException error) {
      showText("This input cannot be used. Use numbers instead :" + error);
      logger.error(error);
    }
    refreshUi();
  }

  public void startView(AppView view){
    ((IApp) view ).createView(this);
  }
}
