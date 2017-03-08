package com.excilys.computerdb.fconsigny.presentation.controller.servlet;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet(urlPatterns = { "/computer/add"})
public class ViewComputerAddController  extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewComputerAddController.class);

	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    RequestDispatcher dispatcher = this.getServletContext()
	        .getRequestDispatcher("/WEB-INF/views/ViewComputerAdd.jsp");
	    dispatcher.forward(request, response);

	  }

	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	  
		  String name = request.getParameter("computerName");
		  String introduced = request.getParameter("introduced");
		  String discontinued = request.getParameter("discontinued"); 
		  String companyId = request.getParameter("companyId");
		  
		  ComputerDto computerDto = new ComputerDto();
		  computerDto.setName(name);
		  computerDto.setInserted(introduced);
		  computerDto.setDiscontinued(discontinued);
		  computerDto.setCompanyId(Integer.parseInt(companyId));
		  
		  try {
			new ComputerServices().saveComputer(ComputerDtoMapper.transformToComputer(computerDto));
			response.sendRedirect("dashboard");
		} catch (SQLException e) {
			logger.error(e);
		}
	  }
}
