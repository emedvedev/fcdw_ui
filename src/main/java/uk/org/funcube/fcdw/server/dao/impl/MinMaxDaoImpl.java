package uk.org.funcube.fcdw.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.MinMaxDao;
import uk.org.funcube.fcdw.server.model.MinMax;
import uk.org.funcube.fcdw.server.model.MinMaxEntity;

public class MinMaxDaoImpl extends AbstractDataAccessObject<MinMax, MinMaxEntity, Long> implements MinMaxDao {

	@Override
	public List<MinMax> findBySatelliteId(long satelliteId) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String queryString = "SELECT mm FROM MinMaxEntity mm " + "WHERE mm.satelliteId = :satelliteId";

		parameters.put("satelliteId", satelliteId);

		return findMany(queryString, parameters);
	}

	@Override
	public List<MinMax> findBySatelliteIdAndChannel(long satelliteId,
			long channel) {

		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String queryString = "SELECT mm FROM MinMaxEntity mm " + "WHERE mm.satelliteId = :satelliteId "
		+ " and mm.channel = :channel";

		parameters.put("satelliteId", satelliteId);
		parameters.put("channel", channel);

		return findMany(queryString, parameters);
	}

}
