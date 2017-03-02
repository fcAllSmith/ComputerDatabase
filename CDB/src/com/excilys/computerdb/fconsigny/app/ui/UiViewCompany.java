package com.excilys.computerdb.fconsigny.app.ui;

import java.io.IOException;

import com.excilys.computerdb.fconsigny.AppView;
import com.excilys.computerdb.fconsigny.app.IApp;
import com.excilys.computerdb.fconsigny.app.controller.CompanyController;

public class UiViewCompany extends AppView implements IApp{

	AppView appParentView ;
	private static CompanyController companyController;

	@Override
	public void CreateView(AppView appParentView) {
		if(this.appParentView == null && companyController == null){
			this.appParentView = appParentView ;
			companyController = new CompanyController(this);
		}
		
		RefreshUi();
	}

	@Override
	public void RefreshUi() {
		showText("------- Menu -------");
		showText("1 - Show list of companies") ;
		showText("2 - Show a company with an ID");
		String str_input;

		try {
			str_input = readInputText();
			onInputKey(str_input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onInputKey(String str_input) throws IOException{
		try{
			int cmd = Integer.parseInt(str_input);
			switch(cmd){
			case 1 : 
				companyController.loadListCompany();
				break;
			case 2:
				showText("Company id : "); 
				String str_companId = readInputText();
				companyController.loadCompanyById(Integer.parseInt(str_companId));
				break;
			default : 
				DestroyView();
				break;
			}
		}catch(NumberFormatException err){
			showText("This input cannot be used. Use numbers instead :" + err);
		}
	}

	@Override
	public void DestroyView() {
		// TODO Auto-generated method stub
		UiViewCompany.companyController = null;
		((IApp) appParentView).RefreshUi();
	}
	
}
