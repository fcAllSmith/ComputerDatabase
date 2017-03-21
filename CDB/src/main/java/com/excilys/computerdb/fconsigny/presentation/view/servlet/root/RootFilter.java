package com.excilys.computerdb.fconsigny.presentation.view.servlet.root;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdb.fconsigny.spring.ApplicationConfig;


/**
 *   
 *   In charge to load the proper ViewController. 
 * @author excilys.
 *
 */
@WebFilter(filterName = "cdbFilter", urlPatterns = {"/*"})
public class RootFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(); 
		ctx.register(ApplicationConfig.class);
		ctx.refresh();
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (needJdbc(httpRequest)) {
			chain.doFilter(httpRequest, response);	
		} else {       
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	private boolean needJdbc(HttpServletRequest request) {
		System.out.println("JDBC Filter");
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;
		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
		}

		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
				.getServletRegistrations();

		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for (ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}

}
