package uk.org.funcube.fcdw.web.controller;

import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(@QueryParam(value = "satelliteId") Long satelliteId) {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("satelliteId", satelliteId);
		return modelAndView;
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public ModelAndView home(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("satelliteId", satelliteId);
		return modelAndView;
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

}
