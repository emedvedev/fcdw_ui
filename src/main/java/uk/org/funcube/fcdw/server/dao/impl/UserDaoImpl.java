// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.UserDao;
import uk.org.funcube.fcdw.server.model.User;
import uk.org.funcube.fcdw.server.model.UserEntity;

public class UserDaoImpl extends AbstractDataAccessObject<User, UserEntity, String> implements UserDao {

	public UserDaoImpl() {
		// Nothing to be done.
	}

	@Override
	public List<User> findByEither(final String userName, final String siteId) {

		final Map<String, Object> parameters = new HashMap<String, Object>();

		final String query = "SELECT ue FROM UserEntity ue " + "WHERE ue.username = :username OR ue.siteId = :siteId";

		parameters.put("username", userName);
		parameters.put("siteId", siteId);

		return findMany(query, parameters);
	}

	@Override
	public User getByUserName(final String userName) {

		final Map<String, Object> parameters = new HashMap<String, Object>();

		final String query = "SELECT ue FROM UserEntity ue " + "WHERE ue.username = :username";

		parameters.put("username", userName);

		return getSingleResult(query, parameters);
	}

	@Override
	public List<User> findBySiteId(final String siteId) {

		final Map<String, Object> parameters = new HashMap<String, Object>();

		final String query = "SELECT ue FROM UserEntity ue " + "WHERE ue.siteId = :siteId";

		parameters.put("siteId", siteId);

		return findMany(query, parameters);
	}

	@Override
	public User findByUserName(final String username) {

		final Map<String, Object> parameters = new HashMap<String, Object>();

		final String query = "SELECT ue FROM UserEntity ue " + "WHERE ue.username = :username";

		parameters.put("username", username);

		return getSingleResult(query, parameters);
	}

	@Override
	public User saveOrUpdate(UserEntity user) {
		return super.save(user);
	}

	@Override
	public List<User> getRanking(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
