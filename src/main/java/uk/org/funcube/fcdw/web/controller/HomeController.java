// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

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
		modelAndView.addObject("showStatus", new Long(0));
		return modelAndView;
	}

	@RequestMapping(value = "secure/home", method = RequestMethod.GET)
	public ModelAndView secureHome(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("satelliteId", satelliteId);
		return modelAndView;
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied() {
		return "error/accessDenied";
	}

}
