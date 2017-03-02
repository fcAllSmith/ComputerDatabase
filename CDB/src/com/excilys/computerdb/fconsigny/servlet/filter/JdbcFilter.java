package com.excilys.computerdb.fconsigny.servlet.filter;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//All request has to go through the /* filter 
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JdbcFilter implements Filter{

	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		if (this.needJdbc(req)) {
			System.out.println("Open Connection for: " + req.getServletPath());
			Connection conn = null;
			try {
				conn = MysqlDatabase.getMySQLConnection();
				conn.setAutoCommit(false);
				SessionUtil.storeConnection(request, conn);
				chain.doFilter(request, response);
				conn.commit();
			} catch (Exception error) {
				error.printStackTrace();
				ConnectionUtil.rollbackQuietly(conn);
			} finally {
				ConnectionUtil.closeQuietly(conn);
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
