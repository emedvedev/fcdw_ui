package uk.org.funcube.fcdw.server.dao;

import java.util.List;

import uk.org.funcube.fcdw.server.model.WOD;
import uk.org.funcube.fcdw.server.model.WODEntity;

/**
 * Data Access Object for retrieving and persisting whole orbit data.
 */
public interface WholeOrbitDataDao extends DataAccessObject<WOD, Long> {

	WOD findFrame(long satelliteId, long sequenceNumber, long frameNumber);

	void saveOrUpdate(WOD wholeOrbitData);

	List<WODEntity> getLatestOrbit(long satelliteId);

}
