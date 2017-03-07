package com.excilys.computerdb.fconsigny.presentation.view.servlet.root;

import java.io.IOException;
import java.sql.Connection;
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

import com.excilys.computerdb.fconsigny.business.factory.ServletFactory;
import com.excilys.computerdb.fconsigny.storage.database.ConnectionUtil;


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

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Connection connection = null;
		if (needJdbc(httpRequest)) {
			try {
				connection = ServletFactory.getSession();	
				ServletFactory.storeConnection(httpRequest, connection);
				chain.doFilter(httpRequest, response);
				connection.commit();

			} catch (Exception error) {
				error.printStackTrace();
				ConnectionUtil.rollbackQuietly(connection);
				ConnectionUtil.closeQuietly(connection);
			} finally {
				ConnectionUtil.closeQuietly(connection);
			}
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

		//Key: servletName.
		// Value: ServletRegistration
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
				.getServletRegistrations();

		// Collection of all servlet in your webapp.
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
