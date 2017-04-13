package com.excilys.computerdb.fconsigny.webapp.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.excilys.computerdb.fconsigny.webapp.config.WebConfig;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container)  {
		System.out.println(WebAppInitializer.class.getSimpleName());
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		container.addListener(new ContextLoaderListener(rootContext));
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(WebConfig.class);

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);;
		dispatcher.addMapping("/");
	}

}
