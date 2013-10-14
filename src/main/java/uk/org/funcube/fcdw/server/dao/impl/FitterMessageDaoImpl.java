package uk.org.funcube.fcdw.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.FitterMessageDao;
import uk.org.funcube.fcdw.server.model.FitterMessage;
import uk.org.funcube.fcdw.server.model.FitterMessageEntity;

public class FitterMessageDaoImpl extends AbstractDataAccessObject<FitterMessage, FitterMessageEntity, Long> implements FitterMessageDao {

	@Override
	public FitterMessage findBySatelliteIdAndMessageText(Long satelliteId, String messageText, Boolean isDebug) {

		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String query = "SELECT fm FROM FitterMessageEntity fm " + "WHERE fm.satelliteId = :satelliteId " + "and debug = :isDebug "
				+ "and fm.messageText = :messageText";

		parameters.put("satelliteId", satelliteId);
		parameters.put("messageText", messageText);
		parameters.put("isDebug", isDebug);

		FitterMessage fitterMessage = null;

		try {
			fitterMessage = findOneOnly(query, parameters);
		} catch (final EntityNotFoundException enfe) {
			// NO-OP
		}

		return fitterMessage;
	}

	@Override
	public FitterMessage saveOrUpdate(final FitterMessage fitterMessage) {
		final FitterMessage fm = super.save(fitterMessage);
		return fm;
	}

	@Override
	public List<FitterMessage> findBySatelliteId(Long satelliteId, Boolean isDebug) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final String queryString = "SELECT fm FROM FitterMessageEntity fm " + "WHERE fm.satelliteId = :satelliteId "
				+ "and fm.debug = :debug " + "and fm.messageText != '' " + "and display = 1 " + "order by fm.lastReceived desc";

		parameters.put("satelliteId", satelliteId);
		parameters.put("debug", isDebug);

		return findMany(queryString, parameters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FitterMessage getCctDebug(long satelliteId) {

		final Query query = getEntityManager().createQuery(
				"SELECT fm FROM FitterMessageEntity fm where fm.satelliteId = :satelliteId " + "and fm.debug = 1"
						+ "order by fm.lastReceived desc");
		query.setParameter("satelliteId", satelliteId);
		query.setMaxResults(1);
		List<FitterMessage> resultList = query.getResultList();
		return (!resultList.isEmpty()) ? resultList.get(0) : null;
	}

}
