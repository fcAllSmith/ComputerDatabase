package com.excilys.computerdb.fconsigny.presentation.view.cli;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.ComputerController;
import com.excilys.computerdb.fconsigny.utils.view.AppView;



public class UiViewComputer extends AppView implements IApp {

	private static Logger logger = Logger.getLogger(UiViewComputer.class);

	AppView appParentView ;
	private  ComputerController computerController;

	@Override
	public void createView(AppView appParentView) {
		if (this.appParentView == null && computerController == null) {
			this.appParentView = appParentView ;
			computerController = new ComputerController(this);
		}
		
		refreshUi();	
	}

	@Override
	public void refreshUi() {
		showText("test -- menu");
		showText("1 - Show list of computers") ;
		showText("2 - Select a computer for info") ;
		showText("3 - Remove a computer from the data base ");
		showText("4 - Create a computer ");
		showText("5 - Update a computer");
		String strInput;
		try {
			strInput = readInputText();
			onInputKey(strInput);
		} catch (IOException error) {
			logger.error(error);			
		}
	}

	@Override
	public void destroyView() {
		((IApp) appParentView).refreshUi();
	}

	public void onInputKey(String strInput) throws IOException {
		int cmd = Integer.parseInt(strInput);
		switch (cmd) {
		case 0: 
			refreshUi();
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
			try {
				computerController.deleteComputer(readInputText());
			} catch (NumberFormatException e) {
				logger.error(e);
			} catch (SQLException e) {
				logger.error(e);
			}
			break;
		case 4:
			computerController.createComputer(null);
		case 5:
			break;
		default:
			this.destroyView();
			break;
		}
		
		refreshUi();
	}
}