package uk.org.funcube.fcdw.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.MinMaxDao;
import uk.org.funcube.fcdw.server.model.MinMax;
import uk.org.funcube.fcdw.server.model.MinMaxEntity;

public class MinMaxDaoImpl extends AbstractDataAccessObject<MinMax, MinMaxEntity, Long> implements MinMaxDao {

	private static Logger LOG = Logger.getLogger(MinMaxDaoImpl.class.getName());

	@Override
	public List<MinMax> findBySatelliteId(long satelliteId) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String queryString = "SELECT mm FROM MinMaxEntity mm " 
		+ " WHERE mm.satelliteId = :satelliteId"
		+ " AND mm.enabled = 1";

		parameters.put("satelliteId", satelliteId);

		return findMany(queryString, parameters);
	}

	@Override
	public List<MinMax> findBySatelliteIdAndChannel(long satelliteId,
			long channel) {

		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String queryString = "SELECT mm FROM MinMaxEntity mm " + "WHERE mm.satelliteId = :satelliteId "
		+ " and mm.channel = :channel order by refDate desc limit 1";

		parameters.put("satelliteId", satelliteId);
		parameters.put("channel", channel);
		
		List<MinMax> channels = findMany(queryString, parameters);

		LOG.debug("Minmax channel count: " + channels.size() 
				+ ", channel: " + channel 
				+ ", refDate: " + channels.get(0).getRefDate().toString());
		
		return channels;
	}

}
