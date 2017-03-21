package com.excilys.computerdb.fconsigny.launcher;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewLauncher;

public class CliLauncher {

	/**
	 *  Create an instance of the LauncherView.
	 *  The first view has none parent view.
	 * @param args : not used here.
	 */
	public static void main(String[] args) {
		//BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource ("spring_context.xml"));
		new UiViewLauncher().createView(null);
	
	}

}
