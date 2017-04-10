package com.excilys.computerdb.fconsigny.cli.view;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.computerdb.fconsigny.cli.application.*;
import com.excilys.computerdb.fconsigny.cli.component.AppView;


public class UiViewLauncher extends AppView implements IApp {

  private static Logger logger = Logger.getLogger(UiViewLauncher.class);
 
  private UiViewComputer uiViewComputer;
  private UiViewCompany uiViewCompany; 
  private UiViewPageComputer uiViewPageComputer; 

  @Override
  public void createView(final IApp appParentView) {
    AnnotationConfigApplicationContext a =  new AnnotationConfigApplicationContext(ApplicationConfig.class);
    uiViewComputer = a.getBean(UiViewComputer.class);
    uiViewCompany = a.getBean(UiViewCompany.class);
    uiViewPageComputer = a.getBean(UiViewPageComputer.class);
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
        startView(uiViewComputer);
        break;
      case 2:
        startView(uiViewCompany);
        break;
      case 3:
        startView(uiViewPageComputer);
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
