package com.excilys.computerdb.fconsigny.presentation.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.cli.UiViewComputer;
import com.excilys.computerdb.fconsigny.presentation.view.servlet.path.ViewPathBuilder;
import com.excilys.computerdb.fconsigny.utils.log.DoLogger;

@WebServlet("/dashboard")
public class ViewDashboardController extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewDashboardController.class);

	private ComputerServices computerServices; 

	public ViewDashboardController(){
		super();
		try {
			this.computerServices = new ComputerServices();
		} catch (SQLException error) {
			error.printStackTrace();
			logger.error(error);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getAttribute("search") != null){
			String name = request.getAttribute("search").toString();
			this.populateListComputer(ComputerDtoMapper.transformListToDto(this.computerServices.getAllComputersWithLimiter(name,0, 10)),request);
		}else{
			this.populateListComputer(ComputerDtoMapper.transformListToDto(this.computerServices.getAllComputers()),request);	
		}

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(ViewPathBuilder.viewPath(this.getClass()));

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void populateListComputer(List<ComputerDto> computerDtoList, HttpServletRequest request) {
		request.setAttribute("ctpFound", computerDtoList.size());
		request.setAttribute("computerList", computerDtoList);
	}
}