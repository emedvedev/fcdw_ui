package uk.org.funcube.fcdw.web.controller;

import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.service.impl.AbstractService;

@Controller
@RequestMapping("observer")
public class ObserverController extends AbstractService {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView observer(@QueryParam(value = "satelliteId") Long satelliteId) {

		satelliteId = (satelliteId != null) ? satelliteId : new Long(2L);

		ModelAndView mv = new ModelAndView("observer");

		mv.addObject("satelliteId", satelliteId);

		return mv;
	}

}
