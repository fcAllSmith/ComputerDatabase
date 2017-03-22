package com.excilys.computerdb.fconsigny.presentation.controller.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdb.fconsigny.presentation.view.cli.IApp;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewPageComputer;
import com.excilys.computerdb.fconsigny.spring.ApplicationConfig;

public class PageController {

  @Autowired
  private ApplicationContext context;
  
  private final UiViewPageComputer view; 
  
  public PageController(IApp view){
    this.view = (UiViewPageComputer) view;
    this.context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  }
}
