package uk.org.funcube.fcdw.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("profile")
public class UserProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showUserProfilePage() {
		return new ModelAndView("profile");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView updateUserProfile() {
		return new ModelAndView("profile");
	}
}
