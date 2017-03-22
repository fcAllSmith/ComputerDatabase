package com.excilys.computerdb.fconsigny.presentation.controller.servlet;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;
import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.presentation.view.servlet.path.ViewPathBuilder;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@WebServlet("/dashboard")
public class ViewDashboardController extends HttpServlet implements Servlet {

  private static final long serialVersionUID = 1L;
  private static Logger logger = Logger.getLogger(ViewDashboardController.class);

  @Autowired
  IComputerServices computerServices;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    computerServices = new ComputerServices();

    if (request.getParameter("search") != null) {
      try {
        this.populateListComputer(ComputerDtoMapper.transformListToDto(
            computerServices.getAllComputersWithLimiter(0, 10, request.getParameter("search"))), request);
      } catch (ServiceException e) {
      //TODO : Define an action
      }

    } else {
      try {
        this.populateListComputer(
            ComputerDtoMapper.transformListToDto(computerServices.getAllComputersWithLimiter(0, 10, null)), request);
      } catch (ServiceException e) {
      //TODO : Define an action
      }
    }

    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher(ViewPathBuilder.viewPath(this.getClass()));

    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  public void populateListComputer(List<ComputerDto> computerDtoList, HttpServletRequest request) {
    request.setAttribute("ctpFound", computerDtoList.size());
    request.setAttribute("computerList", computerDtoList);
  }
}
