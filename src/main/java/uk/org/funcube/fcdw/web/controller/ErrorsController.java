package uk.org.funcube.fcdw.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorsController {

	// cs-allow-non-final-start

	@RequestMapping("accessdenied")
	public String accessDenied() {
		return "403";
	}

	// cs-allow-non-final-end
}
