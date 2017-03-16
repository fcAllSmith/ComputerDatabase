package com.excilys.computerdb.fconsigny.presentation.view.cli;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.CompanyController;
import com.excilys.computerdb.fconsigny.utils.view.AppView;

public class UiViewCompany extends AppView implements IApp {

	AppView appParentView ;
	
	private static Logger logger = Logger.getLogger(UiViewCompany.class);
	private CompanyController companyController;

	@Override
	public void createView(AppView appParentView) {
		if(this.appParentView == null && companyController == null){
			this.appParentView = appParentView ;
			this.companyController = new CompanyController(this);
		}
		refreshUi();
	}

	@Override
	public void refreshUi() {
		showText("------- Menu -------");
		showText("1 - Show list of companies") ;
		showText("2 - Show a company with an ID");
		String strInput;
		try {
			strInput = readInputText();
			onInputKey(strInput);
		} catch (IOException error) {
			logger.error(error);
		}	
	}

	public void onInputKey(String strInput) throws IOException {
		try {
			int cmd = Integer.parseInt(strInput);
			switch (cmd) {
			case 1 : 
				this.companyController.loadListCompany();
				break;
			case 2:
				showText("Company id : "); 
				this.companyController.loadCompanyById(readInputText());
				break;
			default : 
				destroyView();
				break;
			}
		} catch (NumberFormatException error) {
			showText("This input cannot be used. Use numbers instead :" + error);
			logger.error(error);
		}
	}

	@Override
	public void destroyView() {
		this.companyController = null;
		((IApp) appParentView).refreshUi();
	}

}