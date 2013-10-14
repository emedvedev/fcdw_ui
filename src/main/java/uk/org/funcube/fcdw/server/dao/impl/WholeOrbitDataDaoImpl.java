// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.WholeOrbitDataDao;
import uk.org.funcube.fcdw.server.model.WOD;
import uk.org.funcube.fcdw.server.model.WODEntity;

public class WholeOrbitDataDaoImpl extends AbstractDataAccessObject<WOD, WODEntity, Long> implements WholeOrbitDataDao {

	@Override
	public WOD findFrame(final long satelliteId, final long sequenceNumber, final long frameNumber) {

		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String query = "SELECT wod FROM WODEntity wod " + "WHERE wod.satelliteId = :satelliteId "
				+ "and wod.frameNumber = :frameNumber";

		parameters.put("satelliteId", satelliteId);
		parameters.put("frameNumber", frameNumber);

		WOD wod = null;

		try {
			wod = findOneOnly(query, parameters);
		} catch (final EntityNotFoundException enfe) {
			// NO-OP
		}

		return wod;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WODEntity> getLatestOrbit(long satelliteId) {
		final Query query = getEntityManager().createQuery(
				"SELECT wod FROM WODEntity wod where wod.satelliteId = :satelliteId "
						+ "order by wod.sequenceNumber desc, wod.frameNumber asc");
		query.setParameter("satelliteId", satelliteId);
		query.setMaxResults(104);
		return query.getResultList();
	}

	@Override
	public void saveOrUpdate(final WOD wholeOrbitData) {
		super.save(wholeOrbitData);
	}

}
