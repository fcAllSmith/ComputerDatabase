package com.excilys.computerdb.fconsigny.webapp.controller.copy;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@WebServlet(urlPatterns = { "/computer/delete" })
public class ViewComputerDeleteController extends HttpServlet implements Servlet {

  private static final long serialVersionUID = 1L;


  /**
   * Request mapping for user
   */
  @RequestMapping(value = "computer/delete", method = RequestMethod.GET)
  public ModelAndView getDashboardView(HttpServletRequest request) {
    ModelAndView mv= new ModelAndView("ViewComputerDelete");
    return mv; 
  }
}
