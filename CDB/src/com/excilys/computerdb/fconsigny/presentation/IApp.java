package com.excilys.computerdb.fconsigny.presentation;

import com.excilys.computerdb.fconsigny.utils.view.AppView;

public interface IApp {

  public void createView(AppView appParentView);
  
  public void refreshUi();
  
  public void destroyView();

}
