// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

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
	public List<HPEntity> getLatestFourMinutes(long satelliteId) {
		
		Query query = getEntityManager().createQuery(
				"select max(hp.satelliteTime) FROM HPEntity hp where hp.satelliteId = :satelliteId");
        query.setParameter("satelliteId", satelliteId);
        Date lastEntryDate = (Date) query.getSingleResult();
        Date satelliteTime = new Date(lastEntryDate.getTime() - (8 * 60 * 1000));
				
		query = getEntityManager().createQuery(
				"SELECT hp FROM HPEntity hp where hp.satelliteId = :satelliteId "
						+ "and satelliteTime >= :satelliteTime");
		        query.setParameter("satelliteId", satelliteId);
		        query.setParameter("satelliteTime", satelliteTime);
		return (List<HPEntity>)query.getResultList();
	}
	
}
