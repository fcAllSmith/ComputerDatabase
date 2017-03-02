package com.excilys.computerdb.fconsigny.servlet;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdb.fconsigny.model.Computer;
import com.excilys.computerdb.fconsigny.services.ComputerServices;

@WebServlet(urlPatterns = { "/dashboard"})
public class DashboardServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String username = request.getParameter("login");
    request.setAttribute("username", username);

    List<Computer> computerList = new ComputerServices().getAllComputers();
    request.setAttribute("computerList", computerList);

    RequestDispatcher dispatcher = this.getServletContext()
        .getRequestDispatcher("/WEB-INF/views/Dashboard.jsp");
    dispatcher.forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
  
    doGet(request, response);

  }


}
