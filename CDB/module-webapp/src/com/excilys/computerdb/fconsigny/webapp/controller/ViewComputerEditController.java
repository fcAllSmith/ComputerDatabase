package com.excilys.computerdb.fconsigny.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdb.fconsigny.binding.dto.ComputerDto;
import com.excilys.computerdb.fconsigny.binding.mapper.ComputerDtoMapper;
import com.excilys.computerdb.fconsigny.service.exception.ServiceException;
import com.excilys.computerdb.fconsigny.service.services.IComputerServices;

@Controller
public class ViewComputerEditController {
	@Autowired
	IComputerServices computerServices;

	@RequestMapping(value = "computer/edit", method = RequestMethod.GET)
	public ModelAndView getComputerEditView(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("ViewComputerEdit");
		if (request.getParameter("computerId") != null) {
			long id = Integer.parseInt(request.getParameter("computerId"));
			ComputerDto computerDto;
			try {
				computerDto = ComputerDtoMapper.transformToDto(computerServices.getUniqueComputer(id));
				mv.addObject("computer",computerDto);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mv;
	}
}
