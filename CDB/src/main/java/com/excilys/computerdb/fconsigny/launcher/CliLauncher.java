package com.excilys.computerdb.fconsigny.launcher;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;

public class CliLauncher {

	/**
	 *  Create an instance of the LauncherView.
	 *  The first view has none parent view.
	 * @param args : not used here.
	 */
	public static void main(String[] args) {
		new UiViewLauncher().createView(null);
	}

}
