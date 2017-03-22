package com.excilys.computerdb.fconsigny.presentation.controller.servlet;

import com.excilys.computerdb.fconsigny.business.exception.ServiceException;
import com.excilys.computerdb.fconsigny.business.services.ComputerServices;
import com.excilys.computerdb.fconsigny.business.services.IComputerServices;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

@WebServlet(urlPatterns = { "/computer/delete" })
public class ViewComputerDeleteController extends HttpServlet implements Servlet {

  private static final long serialVersionUID = 1L;

  @Autowired
  IComputerServices computerServices;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String strId = request.getParameter("computerId");

    try {
      if (computerServices.deleteComputer(Integer.parseInt(strId))) {
        response.sendRedirect("/CDB/dashboard");
      } else {
        doGet(request, response);
      }
    } catch (NumberFormatException e) {
    //TODO : Define an action
    } catch (ServiceException e) {
    //TODO : Define an action
    }
  }
}
