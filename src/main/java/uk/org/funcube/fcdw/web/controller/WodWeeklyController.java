// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.service.impl.AbstractService;

@Controller
@RequestMapping("wodweekly")
public class WodWeeklyController extends AbstractService {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView wod(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("wodweekly");
		
		File fileLocation = new File(System.getProperty("wodweekly.dir"));

		mv.addObject("satelliteId", satelliteId);
		mv.addObject("showStatus", new Long(0));
		List<String> list = Arrays.asList(fileLocation.list());  
		Collections.sort(list);
		mv.addObject("fileList", list);

		return mv;
	}

}
