// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
	public List<WODEntity> getSince(long satelliteId, Date satelliteTime) {
		final Query query = getEntityManager().createQuery(
				"SELECT wod FROM WODEntity wod where wod.satelliteId = :satelliteId "
						+ " and satelliteTime > :satelliteTime");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("satelliteTime", satelliteTime);
		return query.getResultList();
	}
	
	@Override
	public List<WODEntity> getLastItem(long satelliteId) {
		
		// get the last item
		Query query = getEntityManager().createQuery(
				"select max(wod.id) from WODEntity wod where wod.satelliteId = :satelliteId");
		query.setParameter("satelliteId", satelliteId);
		Long id = (Long) query.getSingleResult();
		
		query = getEntityManager().createQuery(
				"SELECT wod FROM WODEntity wod where wod.id = :id ");
		query.setParameter("id", id);		
		return query.getResultList();
	}

	@Override
	public void saveOrUpdate(final WOD wholeOrbitData) {
		super.save(wholeOrbitData);
	}
	
	@Override
	public List<WODEntity> getLatestOrbit(Long satelliteId) {
		
		// get the last item
		Query query = getEntityManager().createQuery(
				"select max(wod.id) from WODEntity wod where wod.satelliteId = :satelliteId");
		query.setParameter("satelliteId", satelliteId);
		Long id = (Long) query.getSingleResult();
		
		query = getEntityManager().createQuery(
				"SELECT wod FROM WODEntity wod where wod.id > :id ");
		query.setParameter("id", id - 104);		
		return query.getResultList();
	}

}
