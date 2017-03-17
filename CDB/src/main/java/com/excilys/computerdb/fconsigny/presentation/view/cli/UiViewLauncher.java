package com.excilys.computerdb.fconsigny.presentation.view.cli;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.utils.view.AppView;

public class UiViewLauncher extends AppView implements IApp{

	private static Logger logger = Logger.getLogger(UiViewLauncher.class);

	@Override
	public void createView(final AppView appParentView) {
		refreshUi();
	}

	@Override
	public void refreshUi() {
		showText("------- Menu -------");
		showText("1 - Computers") ;
		showText("2 - Companies");
		showText( "What do you want to do ? :");

		onInputKey(readInputText());
	}

	@Override
	public void destroyView() {}

	public void onInputKey(final String strInput) {
		try {
			int cmd = Integer.parseInt(strInput);
			switch (cmd) {
			case 1: 
				UiViewComputer uiViewComputer = new UiViewComputer();
				uiViewComputer.createView(this);
				break; 
			case 2:
				UiViewCompany uiViewCompany = new UiViewCompany();
				uiViewCompany.createView(this);
				break;
			default:
				showText("command not found");
				break; 
			}
		} catch(NumberFormatException error) {
			showText("This input cannot be used. Use numbers instead :" + error);
			logger.error(error);
		}
		refreshUi();
	}
}
