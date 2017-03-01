package com.excilys.computerdb.fconsigny.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.excilys.computerdb.fconsigny.dao.ComputerDaoImpl;
import com.excilys.computerdb.fconsigny.exceptions.ComputerException;
import com.excilys.computerdb.fconsigny.model.Computer;
import com.excilys.computerdb.fconsigny.services.ComputerServices;

public class AppLauncher {

	public static void main(String[] args) {
		showText("-- Welcom  to Computer Database");
		showText("1 - Select all companies");
		showText("2 - Select company with an id");
		showText("3 - Select all computers");
		showText("4 - Select a computer with an id");

		userCommand(getUserCommand());
	
		for(Computer computer : new ComputerServices().getAllComputersWithLimiter(10, 0)){
			showText(computer.toString());
		}
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
		InputStreamReader inputStrReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inputStrReader);
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
