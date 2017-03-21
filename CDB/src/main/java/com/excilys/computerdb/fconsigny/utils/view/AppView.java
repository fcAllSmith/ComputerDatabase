package com.excilys.computerdb.fconsigny.utils.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;

public abstract class AppView {

  private static Logger logger = Logger.getLogger(AppView.class);

  public static void main(String[] args) {
    System.out.println("-- Welcom  to Computer Database --");
    System.out.println("1 - Companies");
    System.out.println("2 - Computers");

    userCommand(getUserCommand());
  }

  public static void userCommand(String strInput) {
    int cmd = Integer.parseInt(strInput);
    switch (cmd) {
    case 1:
      break;
    case 2:
      break;
    default:
      break;
    }
  }

  public static String getUserCommand() {
    String inputText = null;
    InputStreamReader inputStrReader = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(inputStrReader);
    try {
      inputText = br.readLine();
    } catch (IOException error) {
      error.printStackTrace();
    }
    return inputText;
  }

  public String readInputText() {
    String inputText = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      inputText = br.readLine();
    } catch (IOException error) {
      logger.error(error);
      inputText = null;
    }

    return inputText;
  }

}
