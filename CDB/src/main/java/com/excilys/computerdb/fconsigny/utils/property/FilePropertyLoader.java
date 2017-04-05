package com.excilys.computerdb.fconsigny.utils.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FilePropertyLoader {

  public  static Properties loadDatabaseProperties(Class c , String fileName){
    try {
      Properties properties = new Properties();
      ClassLoader classLoader = c.getClassLoader();
      if(classLoader  == null)
        System.out.print("NULL");
      
      String filePath = "properties/database/" + fileName ;
      
      if(filePath != null){
        System.out.print("PATH NULL" + filePath);
      }
      
      File configFile = new File(classLoader.getResource(filePath).getFile());
      properties.load(new FileInputStream(configFile));
      return properties;

    } catch (IOException e) {

      e.printStackTrace();
      return null;
    }
  }
  
  public static Properties loadSqlProperties(Class c , String fileName) {
    try {
      Properties properties = new Properties();
      ClassLoader classLoader = c.getClassLoader();
      File configFile = new File(classLoader.getResource("properties/sql/"+fileName).getFile());
      properties.load(new FileInputStream(configFile));
      return properties;

    } catch (IOException e) {

      e.printStackTrace();
      return null;
    }
  }

}
