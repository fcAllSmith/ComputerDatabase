package src.main.java.com.excilys.computerdb.fconsigny.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdb.fconsigny.presentation.dto.ComputerDto;

@Controller
public class ViewComputerEditController {

  @RequestMapping(value = "computer/edit", method = RequestMethod.GET)
  public ModelAndView getComputerEditView(ComputerDto computerDto) {
    ModelAndView mv= new ModelAndView("ViewComputerEdit");
    mv.addObject("computer",computerDto);
    return mv;
  }
}
