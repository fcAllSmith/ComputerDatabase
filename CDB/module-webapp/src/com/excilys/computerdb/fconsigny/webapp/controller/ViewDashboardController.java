package com.excilys.computerdb.fconsigny.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import com.excilys.computerdb.fconsigny.binding.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.service.services.IComputerServices;

@Controller
public class ViewDashboardController{

  @Autowired
  IComputerServices computerServices;

  /**
   * Request mapping for user
   */
  @RequestMapping(value = "dashboard", method = RequestMethod.GET)
  public ModelAndView getDashboardView(HttpServletRequest request) {
    ModelAndView mv= new ModelAndView("ViewDashboard");
    if (request.getParameter("search") != null) {
      try {
        mv.addObject("computerList",ComputerDtoMapper.transformListToDto(
            computerServices.getAllComputersWithLimiter(0, 10, request.getParameter("search"))));
      } catch (ServiceException e) {
      //TODO : Define an action
      }

    } else {
      try {
        mv.addObject("computerList",
            ComputerDtoMapper.transformListToDto(computerServices.getAllComputersWithLimiter(0, 10, null)));
      } catch (ServiceException e) {
      //TODO : Define an action
      }
    }

    return mv;
  }
}
