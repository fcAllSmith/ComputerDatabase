package com.excilys.computerdb.fconsigny.presentation.view.cli;

import com.excilys.computerdb.fconsigny.spring.Application;
import com.excilys.computerdb.fconsigny.utils.view.AppView;
import org.springframework.context.ApplicationContext;

public interface IApp {
  
  public static ApplicationContext getContext() {
    return new Application().getAppContext();
  } 
  
  public void createView(ApplicationContext context,IApp parentView);

  public void refreshUi();

  public void destroyView();

  public default void showText(String message) {
    System.out.println(message);
  }
}
