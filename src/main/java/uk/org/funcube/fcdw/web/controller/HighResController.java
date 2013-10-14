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

import uk.org.funcube.fcdw.server.service.impl.AbstractService;

@Controller
@RequestMapping("highres")
public class HighResController extends AbstractService {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView highres(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("highres");

		mv.addObject("satelliteId", satelliteId);
		return mv;
	}

}
