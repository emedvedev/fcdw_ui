// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.RealTimeDao;
import uk.org.funcube.fcdw.server.model.RealTimeEntity;

/**
 * Data Access Object for retrieving and persisting hex frames.
 */
public class RealTimeDaoImpl extends AbstractDataAccessObject<RealTimeEntity, RealTimeEntity, Long> implements RealTimeDao {

	public RealTimeDaoImpl() {
		// Nothing to be done.
	}

	@Override
	public List<RealTimeEntity> getByDates(final Date startDate, final Date endDate) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String query = "SELECT rt FROM RealTimeEntity rt " + "WHERE rt.createdDate between :startDate and :endDate";

		parameters.put("startDate", startDate);
		parameters.put("endDate", endDate);

		return findMany(query, parameters);
	}

	@Override
	public RealTimeEntity getLastEntry(final Long satelliteId) {
		final Query query = getEntityManager().createQuery(
				"select rt1 from RealTimeEntity rt1 where rt1.satelliteId = :satelliteId and rt1.sequenceNumber"
						+ " = (select max(rt2.sequenceNumber) from RealTimeEntity rt2 where rt2.satelliteId = :satelliteId)");
		
		query.setParameter("satelliteId", satelliteId);

		RealTimeEntity entry = null;

		final List<RealTimeEntity> realTimeList = findMany(query);

		if (null != realTimeList && realTimeList.size() > 0) {
			entry = realTimeList.get(0);
		}

		return entry;
	}

	@Override
	public Long countAll() {
		final Query query = getEntityManager().createQuery("SELECT count(rt) FROM RealTimeEntity rt");
		return (Long) query.getSingleResult();
	}

	@Override
	public RealTimeEntity findBySeqNoAndSrcFrameId(long sequenceNumber, long srcFrameId) {

		final Map<String, Object> parameters = new HashMap<String, Object>();

		final String query = "select rt from RealTimeEntity rt where rt.sequenceNumber " + "= :sequenceNumber AND srcFrameId = :srcFrameId";

		parameters.put("sequenceNumber", sequenceNumber);
		parameters.put("srcFrameId", srcFrameId);

		RealTimeEntity entry = null;

		final List<RealTimeEntity> realTimeList = findMany(query, parameters);

		if (null != realTimeList && realTimeList.size() > 0) {
			entry = realTimeList.get(0);
		}

		return entry;
	}

	@Override
	public RealTimeEntity saveOrUpdate(final RealTimeEntity realTime) {
		RealTimeEntity rt = super.save(realTime);
		return rt;
	}
}
