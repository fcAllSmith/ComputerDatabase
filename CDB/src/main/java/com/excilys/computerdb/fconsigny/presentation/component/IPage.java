package com.excilys.computerdb.fconsigny.presentation.component;

public interface IPage <T> {

  public int getOffset();
  
  public int getLimite();
  
  public int getCurrentPage();
  
  public void setMaxCount(int nbrMax);
}