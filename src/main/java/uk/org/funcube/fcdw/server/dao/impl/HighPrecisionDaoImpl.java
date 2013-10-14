package uk.org.funcube.fcdw.server.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import uk.org.funcube.fcdw.server.dao.AbstractDataAccessObject;
import uk.org.funcube.fcdw.server.dao.HighPrecisionDao;
import uk.org.funcube.fcdw.server.model.HPEntity;
import uk.org.funcube.fcdw.server.model.HighPrecision;

/**
 * Data Access Object for retrieving and persisting hex frames.
 */
public class HighPrecisionDaoImpl 
    extends AbstractDataAccessObject<HighPrecision, HPEntity, Long> 
    implements HighPrecisionDao {

    public HighPrecisionDaoImpl() {
        // Nothing to be done.
    }

   @Override
	public Long countAll() {
		final Query query = getEntityManager().createQuery("SELECT count(hp) FROM HPEntity hp");
		return (Long)query.getSingleResult();
	}

    @Override
	public List<HighPrecision> findByTimeRange(final Date start, final Date end) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
        final String query = "SELECT hp FROM HPEntity hp "
                + "WHERE hp.receivedDate BETWEEN :start AND :end ORDER BY hp.receivedDate";

        parameters.put("start", start);
        parameters.put("end", end);

        final List<HighPrecision> data = findMany(query, parameters);
        
        return data;
	}
	
    @Override
	public HighPrecision saveOrUpdate(final HighPrecision highPrecisionData) {
	    
    	final HighPrecision hp = super.save(highPrecisionData);
		return hp;
	}

	@Override
	public Date getLastFrameTime() {
		final Date epoch = Calendar.getInstance().getTime();
		epoch.setTime(0);
		final Query query = getEntityManager().createQuery("SELECT max(receivedDate) FROM HPEntity hp");
		final Date lastFrameTime = (Date)query.getSingleResult();
		return (lastFrameTime != null) ? lastFrameTime : epoch;
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<HPEntity> getLatestSixtySeconds(long satelliteId) {
		final Query query = getEntityManager().createQuery(
				"SELECT hp FROM HPEntity hp where hp.satelliteId = :satelliteId "
						+ "order by hp.sequenceNumber desc, hp.id asc");
		        query.setParameter("satelliteId", satelliteId);
		        query.setMaxResults(60);
		return (List<HPEntity>)query.getResultList();
	}
	
}
