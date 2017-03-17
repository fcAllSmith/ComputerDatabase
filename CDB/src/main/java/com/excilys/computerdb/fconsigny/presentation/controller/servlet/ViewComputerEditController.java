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

import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

@WebServlet(urlPatterns = { "/computer/edit"})
public class ViewComputerEditController extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("computerId"));
		if(request.getParameter("computerId") != null && Integer.parseInt(request.getParameter("computerId").toString()) > 0){	
		
			try {
				populateComputer(
						ComputerDtoMapper.transformToDto(new ComputerServices().getUniqueComputer(Integer.parseInt(request.getParameter("computerId").toString()))),request);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} 
		} 

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/ViewComputerEdit.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("computerName");
		ComputerDto computerDto = new ComputerDto();
		System.out.println(request.getParameter("computerId"));
		computerDto.setId(Integer.parseInt(request.getParameter("computerId")));
		computerDto.setName(request.getParameter("computerName"));
		computerDto.setIntroduced(request.getParameter("introduced"));
		computerDto.setDiscontinued("discontinued");
		computerDto.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
		
		if(new ComputerServices().editComptuter(ComputerDtoMapper.transformToComputer(computerDto))){
			response.sendRedirect("/CDB/dashboard");
		} else {
			doGet(request,response);
		}
	}

	public void populateComputer(ComputerDto computerDto,HttpServletRequest request ){
		request.setAttribute("id", computerDto.getId());
		request.setAttribute("name", computerDto.getName());
		request.setAttribute("introduced", computerDto.getIntroduced());
		request.setAttribute("discontinued", computerDto.getDiscontinued());
		request.setAttribute("companyId", computerDto.getCompanyId());
	}
}
