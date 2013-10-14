// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.org.funcube.fcdw.server.dao.impl.UserDaoImpl;
import uk.org.funcube.fcdw.server.model.User;
import uk.org.funcube.fcdw.server.model.UserEntity;
import uk.org.funcube.fcdw.server.service.impl.AbstractService;
import uk.org.funcube.fcdw.server.service.impl.MailService;
import uk.org.funcube.fcdw.web.security.ReasonablePasswordGenerator;
import uk.org.funcube.fcdw.web.viewmodel.RegisterUserRequest;

@Controller
@RequestMapping("register")
@Transactional(readOnly = false)
public class RegisterController extends AbstractService {

	private static final Logger LOGGER = Logger.getLogger(RegisterController.class);

	@Autowired
	UserDaoImpl userDao;

	@Autowired
	ReasonablePasswordGenerator passwordGenerator;

	@Autowired
	MailService mailService;

	@RequestMapping(method = RequestMethod.GET)
	public String loadFormPage(Model m) {
		m.addAttribute("registerUserRequest", new RegisterUserRequest());
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public String submitForm(@ModelAttribute("registerUserRequest") @Valid RegisterUserRequest registerUserRequest, BindingResult result,
			Model m) {
		if (result.hasErrors()) {
			return "register";
		}

		String password = passwordGenerator.generatePassword();
		String authKey = RandomStringUtils.random(20, true, true);
		String passwordHash;
		try {
			passwordHash = passwordGenerator.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
			passwordHash = "xxxxxxxxxx";
		}

		UserEntity newUser = new UserEntity(registerUserRequest.getEmail1(), passwordHash, registerUserRequest.getLatitude(),
				registerUserRequest.getLongitude(), registerUserRequest.getSiteName(),
				/* enabled */false,
				/* admin */false,
				/* expired */false,
				/* locked */false,
				/* credentialsExpired */false, authKey,
				/* emailSent */false);

		User existingUser = userDao.findByUserName(registerUserRequest.getEmail1());

		Map<String, Object> emailTags = new HashMap<String, Object>();

		emailTags.put("siteName", registerUserRequest.getSiteName());
		emailTags.put("password", password);
		emailTags.put("authKey", authKey);

		if (existingUser != null) {
			m.addAttribute("error", "The email address is already registered in the system");
			return "register";
		}

		userDao.save(newUser);

		mailService.sendUsingTemplate(registerUserRequest.getEmail1(), emailTags, "registrationConfirmationEmail");

		m.addAttribute("message", "An email with your login/authentication details will be sent in a couple of minutes");
		return "register";
	}

}
