package com.excilys.computerdb.fconsigny.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AppLauncher {

	public static void main(String[] args) {
		showText("-- Welcom  to Computer Database");
		showText("1 - Select all companies");
		showText("2 - Select company with an id");
		showText("3 - Select all computers");
		showText("4 - Select a computer with an id");
		
		userCommand(getUserCommand());
	}
	
	public static void userCommand(String strInput){
		int cmd = Integer.parseInt(strInput); 
		switch(cmd){
		case 1:
			break; 
		case 2: 
			break; 
		case 3:
			break;
		case 4: 
			break;
		default:
			
			break; 
		}
	}
	
	public static String getUserCommand(){
		String inputText = null; 
		BufferedReader br = new BufferedReader(new InputStream(System.in));
		try {
			inputText = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputText;
	}
	
	public static void showText(String strToPrint){
		System.out.println(strToPrint);
	}
}
