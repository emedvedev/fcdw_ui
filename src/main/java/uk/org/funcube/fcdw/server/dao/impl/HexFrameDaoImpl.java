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
import uk.org.funcube.fcdw.server.dao.HexFrameDao;
import uk.org.funcube.fcdw.server.model.HexFrame;
import uk.org.funcube.fcdw.server.model.HexFrameEntity;

/**
 * Data Access Object for retrieving and persisting hex frames.
 */
public class HexFrameDaoImpl extends AbstractDataAccessObject<HexFrame, HexFrameEntity, Long> implements HexFrameDao {

	public HexFrameDaoImpl() {
		// Nothing to be done.
	}

	@Override
	public Long countAll(final long satelliteId) {
		final Query query = getEntityManager().createQuery("SELECT count(hf) FROM HexFrameEntity hf where hf.satelliteId = :satelliteId");
		query.setParameter("satelliteId", satelliteId);
		return (Long) query.getSingleResult();
	}

	@Override
	public Long countAllValid(final long satelliteId) {
		final Query query = getEntityManager().createQuery(
				"SELECT count(hf) FROM HexFrameEntity hf where hf.satelliteId = :satelliteId AND hf.valid = 1");
		query.setParameter("satelliteId", satelliteId);
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HexFrame> findAllUnprocessedWOD(final long satelliteId, final Date dateAfter) {
		final Query query = getEntityManager().createQuery(
				"SELECT hf FROM HexFrameEntity hf where hf.satelliteId = :satelliteId "
						+ "and hf.valid = 1 and hf.frameType between 0 and 11 " + "and hf.wodProcessed = 0 "
						+ "and createdDate > :dateAfter " + "group by hf.sequenceNumber,hf.frameType");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("dateAfter", dateAfter);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HexFrame> findUnprocessedRealtime(final long satelliteId) {
		final Query query = getEntityManager().createQuery(
				"SELECT hf FROM HexFrameEntity hf where hf.satelliteId = :satelliteId " + "and hf.valid = 1 and hf.realtimeProcessed = 0 ");
		query.setParameter("satelliteId", satelliteId);
		return query.getResultList();
	}

	@Override
	public List<HexFrame> findBySatelliteIdSequenceNumberAnFrameType(final long satelliteId, final long frameType, final long sequenceNumber) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String query = "SELECT hf FROM HexFrameEntity hf "
				+ "WHERE hf.satelliteId = :satelliteId AND hf.sequenceNumber = :sequenceNumber AND hf.frameType = :frameType";

		parameters.put("satelliteId", satelliteId);
		parameters.put("frameType", frameType);
		parameters.put("sequenceNumber", sequenceNumber);

		return findMany(query, parameters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HexFrame> findUnprocessedHighPrecision(final long satelliteId, final Date dateAfter) {
		final Query query = getEntityManager().createQuery(
				"SELECT hf FROM HexFrameEntity hf where hf.satelliteId = :satelliteId "
						+ "and hf.valid = 1 and hf.frameType in (12,16,20) " + "and hf.highPrecisionProcessed = 0 "
						+ "and createdDate > :dateAfter " + "group by hf.sequenceNumber,hf.frameType");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("dateAfter", dateAfter);
		return query.getResultList();
	}

	@Override
	public HexFrame saveOrUpdate(final HexFrame hexFrame) {
		final HexFrame hf = super.save(hexFrame);
		return hf;
	}

	@Override
	public void setHighPrecisionProcessed(final long satelliteId, final long sequenceNumber) {
		final Query query = getEntityManager().createQuery(
				"update HexFrameEntity hfe set hfe.highPrecisionProcessed = 1 "
						+ "where hfe.satelliteId = :satelliteId AND hfe.sequenceNumber = :sequenceNumber");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("sequenceNumber", sequenceNumber);
		query.executeUpdate();
	}

	@Override
	public void setWodProcessed(final long satelliteId, final long sequenceNumber) {
		final Query query = getEntityManager().createQuery(
				"update HexFrameEntity hfe set hfe.wodProcessed = 1 "
						+ "where hfe.satelliteId = :satelliteId AND hfe.sequenceNumber = :sequenceNumber");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("sequenceNumber", sequenceNumber);
		query.executeUpdate();

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<HexFrame> findUnprocessedFitter(final long satelliteId, final Date dateAfter) {
		final Query query = getEntityManager().createQuery(
				"SELECT hf FROM HexFrameEntity hf where hf.satelliteId = :satelliteId "
						+ "and hf.valid = 1 and hf.frameType in (13,14,15,17,18,19,21,22,23) " + "and hf.fitterProcessed = 0 "
						+ "and createdDate > :dateAfter " + "group by hf.sequenceNumber,hf.frameType");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("dateAfter", dateAfter);
		return query.getResultList();
	}

	@Override
	public HexFrame getLatest(long satelliteId) {
		Query query;
		if (satelliteId != 1) {
			query = getEntityManager().createQuery(
					"SELECT hf FROM HexFrameEntity hf where hf.satelliteId = :satelliteId " + "and hf.valid = 1 "
							+ "order by hf.sequenceNumber desc, hf.frameType desc");
		} else {
			query = getEntityManager().createQuery(
					"SELECT hf1 FROM HexFrameEntity hf1 where hf1.satelliteId = :satelliteId and hf1.id = " +
							"(select max(hf2.id) from HexFrameEntity hf2 where hf2.satelliteId = :satelliteId " + "and hf2.valid = 1)");
		}
		query.setParameter("satelliteId", satelliteId);
		query.setMaxResults(1);

		@SuppressWarnings("unchecked")
		List<HexFrame> resultList = query.getResultList();

		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

	@Override
	public List<HexFrame> getOrbitFrames(long satelliteId, Date createdDate) {
		final Query query = getEntityManager().createQuery(
				"SELECT hf FROM HexFrameEntity hf where hf.satelliteId = :satelliteId " + "and hf.valid = 1 "
						+ "and createdDate > :createdDate");
		query.setParameter("satelliteId", satelliteId);
		query.setParameter("createdDate", createdDate);
		query.setMaxResults(1);
		return query.getResultList();
	}
	
	@Override
	public Long getMaxSequenceNumber(long satelliteId) {
		final Query query = getEntityManager().createQuery(
				"select max(hf.sequenceNumber) from HexFrameEntity  hf where hf.satelliteId = :satelliteId");
		query.setParameter("satelliteId", satelliteId);
		return (Long) query.getSingleResult();
	}
}
