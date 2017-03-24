package com.excilys.computerdb.fconsigny.launcher;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.spring.Application;
import com.excilys.computerdb.fconsigny.spring.CliConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CliLauncher {

  public static void main(String[] args) {
  //  ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
   // context.refresh();
  //  Application app = new Application();
    //AnnotationConfigApplicationContext a =  new AnnotationConfigApplicationContext(ApplicationConfig.class);
    
    new UiViewLauncher().createView(null);
    //a.close();
    //a.destroy();
  }
}
