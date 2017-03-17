package com.excilys.computerdb.fconsigny.presentation.view.servlet.path;

public abstract class ViewPathBuilder {

	
	public static String viewPath(final Class inputClass){
		String className = inputClass.getSimpleName();
		String[] splitClassName = className.split("Controller");
		return "/WEB-INF/views/" + splitClassName[0] + ".jsp" ;
	}
}
