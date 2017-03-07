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
import javax.sql.DataSource;

import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.servlet.path.ViewPathBuilder;
import com.excilys.computerdb.fconsigny.utils.log.DoLogger;

@WebServlet("/dashboard")
public class ViewDashboardController extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private ComputerServices computerServices; 

	public ViewDashboardController(){
		super();
		try {
			this.computerServices = new ComputerServices();
		} catch (SQLException error) {
			error.printStackTrace();
			DoLogger.doLog(this.getClass(), "Database can't be reach");
		}
	}

	public void init(){

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//try {
		//Connection connection = ServletFactory.getStoredConnection(request);
		//ServletFactory.storeConnection(request, connection);

		this.populateListComputer(request);

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(ViewPathBuilder.viewPath(this.getClass()));

		dispatcher.forward(request, response);

		//} catch (SQLException error) {
		//response.sendError(500, "Exception sur l'accès à la BDD " + error);
		//	error.printStackTrace();
		//}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void populateListComputer(HttpServletRequest request){
		List<ComputerDto> computerDtoList = this.computerServices.getAllComputers();
		request.setAttribute("ctpFound", computerDtoList.size());
		request.setAttribute("computerList", computerDtoList);
	}
}
