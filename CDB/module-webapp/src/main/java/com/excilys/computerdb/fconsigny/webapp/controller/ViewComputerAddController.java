package src.main.java.com.excilys.computerdb.fconsigny.webapp.controller;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@WebServlet(urlPatterns = { "/computer/add" })
public class ViewComputerAddController extends HttpServlet implements Servlet {

  /**
   * Request mapping for user
   */
  @RequestMapping(value = "computer/add", method = RequestMethod.GET)
  public ModelAndView getDashboardView(HttpServletRequest request) {
    ModelAndView mv= new ModelAndView("ViewComputerAdd");
    return mv; 
  }
}
