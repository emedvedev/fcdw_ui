// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import uk.org.funcube.fcdw.server.dao.UserDao;
import uk.org.funcube.fcdw.server.model.UserEntity;

public final class AccountAuthenticator extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountAuthenticator.class);

	private final PasswordGenerator passwordChecker;
	private final UserDao userDao;

	public AccountAuthenticator(final PasswordGenerator passwordService, final UserDao userDao) {
		this.passwordChecker = passwordService;
		this.userDao = userDao;
	}

	@Override
	public void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) {
		if (!passwordChecker.passwordMatches(userDetails.getPassword(), authentication.getCredentials().toString())) {
			LOGGER.info("Password check failed for user {}", userDetails);
			throw new BadCredentialsException("Unrecognised username/password");
		}
	}

	@Override
	public UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
		final UserEntity user = (UserEntity) userDao.getByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		final List<GrantedAuthority> perms = new ArrayList<GrantedAuthority>();
		perms.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (user.isAdmin()) {
			perms.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new User(username, user.getPassword(), perms);
	}

}
