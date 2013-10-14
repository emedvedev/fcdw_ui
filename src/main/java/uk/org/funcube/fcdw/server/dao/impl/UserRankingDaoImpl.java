// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.UserRankingDao;
import uk.org.funcube.fcdw.server.model.UserRanking;
import uk.org.funcube.fcdw.server.model.UserRankingEntity;

public class UserRankingDaoImpl extends AbstractDataAccessObject<UserRanking, UserRankingEntity, Long> implements UserRankingDao {

	@Override
	public List<UserRanking> findBySatelliteId(Long satelliteId) {

		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String query = "SELECT ur FROM UserRankingEntity ur " + "WHERE ur.satelliteId = :satelliteId order by ur.number desc";

		parameters.put("satelliteId", satelliteId);

		List<UserRanking> userRankings = Collections.emptyList();

		try {
			userRankings = findMany(query, parameters);
		} catch (final EntityNotFoundException enfe) {
			// NO-OP
		}

		return userRankings;
	}

}
