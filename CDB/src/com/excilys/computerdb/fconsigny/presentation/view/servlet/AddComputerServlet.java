package com.excilys.computerdb.fconsigny.presentation.view.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.fconsigny.presentation.controller.cli.CompanyController;
import com.excilys.computerdb.fconsigny.presentation.dto.CompanyDto;

@WebServlet(urlPatterns = { "/computer"})
public class AddComputerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private CompanyController companyController;
	private List<CompanyDto> companyList;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		List<ComputerDto> computerDtoList = 
		
		request.setAttribute("companyList", companyList);
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/AddComputerFile.jsp");

		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
