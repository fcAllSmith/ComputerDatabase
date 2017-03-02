package com.excilys.computerdb.fconsigny.app.ui;

import java.io.IOException;
import java.util.List;

import com.excilys.computerdb.fconsigny.AppView;
import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.controller.CompanyController;
import com.excilys.computerdb.fconsigny.app.controller.ComputerController;
import com.excilys.computerdb.fconsigny.exceptions.CompanyException;
import com.excilys.computerdb.fconsigny.model.Company;
import com.excilys.computerdb.fconsigny.services.CompanyServices;

public class UiViewComputer extends AppView implements IApp {

	AppView appParentView ;
	private  ComputerController computerController;

	@Override
	public void CreateView(AppView appParentView) {
		if(this.appParentView == null && computerController == null){
			this.appParentView = appParentView ;
			computerController = new ComputerController(this);
		}

		RefreshUi();	
	}

	@Override
	public void RefreshUi() {
		showText("test -- menu");
		showText("1 - Show list of computers") ;
		showText("2 - Select a computer for info") ;
		showText("3 - Remove a computer from the data base ");
		showText("4 - Create a computer ");
		showText("5 - Update a computer");
		String str_input;

		try {
			str_input = readInputText();
			onInputKey(str_input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void DestroyView() {
		((IApp) appParentView).RefreshUi();
	}

	public void onInputKey(String str_input) throws IOException{
		int cmd = Integer.parseInt(str_input);

		switch(cmd){

		case 0: 
			RefreshUi();
			break;
		case 1:
			computerController.loadListComputer();
			break ;
		case 2:
			showText("Computer id  : ");
			computerController.loadComputerById(readInputText());
			break;
		case 3:
			showText("Computer id : ");
			computerController.deleteComputer(readInputText());
			break;
		default:
			this.DestroyView();
			break;
		}

		RefreshUi();
	}
}
