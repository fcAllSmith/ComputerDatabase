package com.excilys.computerdb.fconsigny.presentation.controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.fconsigny.business.factory.ServletFactory;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.servlet.path.ViewPathBuilder;
import com.excilys.computerdb.fconsigny.utils.log.DoLogger;

@WebServlet("/dashboard")
public class ViewDashboardController extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection connection = ServletFactory.getStoredConnection(request);
		
		this.populateListComputer(request);

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(ViewPathBuilder.viewPath(this.getClass()));
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	private void populateListComputer(HttpServletRequest request){
		List<ComputerDto> computerDtoList;
		try {
			computerDtoList = new ComputerServices().getAllComputers() ;
			request.setAttribute("actorslist", computerDtoList);
		} catch (SQLException e) {
			DoLogger.doLog(this.getClass(), "Database can't be reach"); 
			e.printStackTrace();
		}
	}
}
