package com.excilys.computerdb.fconsigny.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
  
   private final ApplicationContext appContext; 
   
   public Application() {
       this.appContext = new AnnotationConfigApplicationContext(CliConfig.class);
   }
   
   public ApplicationContext getAppContext(){
     return this.appContext;
   }
}