package src.main.java.com.excilys.computerdb.fconsigny.webapp.controller;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

//@WebServlet(urlPatterns = { "/computer/add" })
public class ViewComputerAddController extends HttpServlet implements Servlet {

  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(ViewComputerAddController.class);

  @Autowired
  IComputerServices computerServices;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ViewComputerAdd.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String name = request.getParameter("computerName");
    String introduced = request.getParameter("introduced");
    String discontinued = request.getParameter("discontinued");
    String companyId = request.getParameter("companyId");

    System.out.println(name);
    System.out.println(introduced);
    System.out.println(discontinued);
    System.out.println(companyId);

    ComputerDto computerDto = new ComputerDto();
    computerDto.setName(name);
    computerDto.setIntroduced(introduced);
    computerDto.setDiscontinued(discontinued);
    computerDto.setCompanyId(Integer.parseInt(companyId));
    try {
      if (computerServices.saveComputer(ComputerDtoMapper.transformToComputer(computerDto))) {
        response.sendRedirect("/CDB/dashboard");
      } else {
        doGet(request, response);
      }
    } catch (ServiceException e) {
      //TODO : Define an action
    }
  }
}
