package uk.org.funcube.fcdw.server.dao;

import java.util.Date;
import java.util.List;

import uk.org.funcube.fcdw.server.model.RealTimeEntity;

/**
 * Data Access Object for retrieving and persisting real time data.
 */
public interface RealTimeDao extends DataAccessObject<RealTimeEntity, Long> {

	List<RealTimeEntity> getByDates(final Date startDate, final Date endDate);

	RealTimeEntity getLastEntry();

	void delete(final RealTimeEntity realTime);

	Long countAll();

	RealTimeEntity findBySeqNoAndSrcFrameId(long sequenceNumber, long srcFrameId);

	RealTimeEntity saveOrUpdate(RealTimeEntity realTime);

}
