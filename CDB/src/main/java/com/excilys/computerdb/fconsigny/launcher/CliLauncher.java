package com.excilys.computerdb.fconsigny.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;
import com.excilys.computerdb.fconsigny.spring.ApplicationConfig;

public class CliLauncher {

	/**
	 *  Create an instance of the LauncherView.
	 *  The first view has none parent view.
	 * @param args : not used here.
	 */
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		new UiViewLauncher().createView(null);
	}
}
