package uk.org.funcube.fcdw.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.SatelliteStatusDao;
import uk.org.funcube.fcdw.server.model.SatelliteStatus;
import uk.org.funcube.fcdw.server.model.SatelliteStatusEntity;

public class SatelliteStatusDaoImpl extends AbstractDataAccessObject<SatelliteStatus, SatelliteStatusEntity, Long> implements SatelliteStatusDao {

	@Override
	public List<SatelliteStatus> findBySatelliteId(long satelliteId) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String queryString = "SELECT mm FROM SatelliteStatusEntity mm " 
		+ " WHERE mm.satelliteId = :satelliteId";

		parameters.put("satelliteId", satelliteId);

		return findMany(queryString, parameters);
	}

}
