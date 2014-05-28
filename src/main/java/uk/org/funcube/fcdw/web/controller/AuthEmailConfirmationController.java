// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uk.org.funcube.fcdw.server.clock.UTCClock;
import uk.org.funcube.fcdw.server.dao.UserDao;
import uk.org.funcube.fcdw.server.model.User;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;

@Controller
@RequestMapping("authemail")
public class AuthEmailConfirmationController extends AbstractService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UTCClock clock;

	@RequestMapping(method = RequestMethod.GET)
	@Transactional(readOnly = false)
	public ModelAndView doValidation(
			@RequestParam(value = "s") String siteId,
			@RequestParam(value = "r") String registrationCode) {
		
		ModelAndView m = new ModelAndView("authemail");
		m.addObject("satelliteId", 2);
		m.addObject("showStatus", new Long(0));
		
		List<User> users = userDao.findBySiteId(siteId);
		
		if (users.size() == 0) {
			m.addObject("error", "Site Name was not found");
			return m;
		}
		
		UserEntity user = (UserEntity) users.get(0);
		
		if (!registrationCode.equals(user.getRegistrationCode())) {
			m.addObject("error", "Registration code was not found");
			return m;
		}
		
		if ((clock.currentTime() - user.getCreatedDate().getTime()) > 6048e5) {
			m.addObject("error", "Registration code has expired, please contact admin@funcube.org.uk");
			return m;
		}
		
		user.setEnabled(true);
		
		userDao.save(user);
		
		return new ModelAndView("authemail", "satelliteId", 2);
	}

}
