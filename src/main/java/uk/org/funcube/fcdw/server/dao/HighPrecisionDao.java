package uk.org.funcube.fcdw.server.dao;

import java.util.Date;
import java.util.List;

import uk.org.funcube.fcdw.server.model.HPEntity;
import uk.org.funcube.fcdw.server.model.HighPrecision;

/**
 * Data Access Object for retrieving and persisting high precision data.
 */
public interface HighPrecisionDao extends DataAccessObject<HighPrecision, Long> {
	
	Long countAll();
	List<HighPrecision>  findByTimeRange(Date start, Date end);
	HighPrecision saveOrUpdate(HighPrecision highPrecisionData);
	Date getLastFrameTime();
	List<HPEntity> getLatestSixtySeconds(long satelliteId);

}
