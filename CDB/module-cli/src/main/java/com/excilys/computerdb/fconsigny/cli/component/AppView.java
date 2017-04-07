package src.main.java.com.excilys.computerdb.fconsigny.cli.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AppView {

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
      inputText = null;
    }

    return inputText;
  }
}
