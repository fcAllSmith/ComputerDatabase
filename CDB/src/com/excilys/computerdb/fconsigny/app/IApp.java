package com.excilys.computerdb.fconsigny.app;

import com.excilys.computerdb.fconsigny.AppView;

public interface IApp {

  public void createView(AppView appParentView);
  
  public void refreshUi();
  
  public void destroyView();

}
