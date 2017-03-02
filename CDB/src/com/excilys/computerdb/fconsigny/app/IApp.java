package com.excilys.computerdb.fconsigny.app;

import com.excilys.computerdb.fconsigny.AppView;

public interface IApp {

	public void CreateView(AppView appParentView);
	public void RefreshUi();
	public void DestroyView();

}
