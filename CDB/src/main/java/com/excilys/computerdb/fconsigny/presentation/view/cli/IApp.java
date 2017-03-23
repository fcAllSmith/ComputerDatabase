package com.excilys.computerdb.fconsigny.presentation.view.cli;

public interface IApp {
  
  public void createView(IApp parentView);

  public void refreshUi();

  public void destroyView();

  public default void showText(String message) {
    System.out.println(message);
  }
}
