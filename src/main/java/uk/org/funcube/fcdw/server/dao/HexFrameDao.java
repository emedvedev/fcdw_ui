package uk.org.funcube.fcdw.server.dao;

import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import uk.org.funcube.fcdw.server.model.HexFrame;

/**
 * Data Access Object for retrieving and persisting hex frames.
 */
public interface HexFrameDao extends DataAccessObject<HexFrame, Long> {

	Long countAll(long satelliteId);

	Long countAllValid(long satelliteId);

	List<HexFrame> findAllUnprocessedWOD(long satelliteId, Date dateAfter);

	List<HexFrame> findBySatelliteIdSequenceNumberAnFrameType(long satelliteId, long frameType, long sequenceNumber);

	List<HexFrame> findUnprocessedHighPrecision(long satelliteId, Date dateAfter);

	List<HexFrame> findUnprocessedFitter(long satelliteId, Date dateAfter);

	HexFrame saveOrUpdate(HexFrame hexFrame);

	void setHighPrecisionProcessed(long satelliteId, long sequenceNumber);

	void setWodProcessed(long satelliteId, long sequenceNumber);

	@Cacheable(value = { "latestHexFrame" })
	HexFrame getLatest(long satelliteId);

	List<HexFrame> getOrbitFrames(long satelliteId, Date date);

	List<HexFrame> findUnprocessedRealtime(long satelliteId);
}
