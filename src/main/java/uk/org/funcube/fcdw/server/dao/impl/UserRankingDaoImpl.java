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
