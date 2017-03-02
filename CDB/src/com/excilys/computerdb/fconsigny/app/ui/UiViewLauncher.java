package com.excilys.computerdb.fconsigny.app.ui;

import java.io.IOException;

import com.excilys.computerdb.fconsigny.AppView;
import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.controller.CompanyController;

public class UiViewLauncher extends AppView implements IApp{

	@Override
	public void CreateView(AppView appParentView) {
		RefreshUi();
	}

	@Override
	public void RefreshUi() {

		showText("------- Menu -------");
		showText("1 - Computers") ;
		showText("2 - Companies");
		showText( "What do you want to do ? :");
		
		readInputText();
		onInputKey(readInputText());
	}

	@Override
	public void DestroyView() {

	}
	
	public void onInputKey(String str_input){

		try{
			int cmd = Integer.parseInt(str_input);
			switch(cmd){
			case 0: 
				//TODO : exit
				break;
			case 1: 
				UiViewComputer uiViewComputer = new UiViewComputer();
				uiViewComputer.CreateView(this);
				break; 
			case 2:
				UiViewCompany uiViewCompany = new UiViewCompany();
				uiViewCompany.CreateView(this);
				break;
			default:
				showText("command not found");
				break; 
			}

		}catch(NumberFormatException err){
			showText("This input cannot be used. Use numbers instead :" + err);
		}

		RefreshUi();
	}
}
