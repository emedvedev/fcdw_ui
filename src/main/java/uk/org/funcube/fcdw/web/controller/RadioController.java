package uk.org.funcube.fcdw.web.controller;

import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.service.impl.AbstractService;

@Controller
@RequestMapping("radio")
public class RadioController extends AbstractService {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView radio(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("radio");

		mv.addObject("satelliteId", satelliteId);

		return mv;
	}
}
