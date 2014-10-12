// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

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

	HexFrame getLatest(long satelliteId);

	List<HexFrame> getOrbitFrames(long satelliteId, Date date);

	List<HexFrame> findUnprocessedRealtime(long satelliteId);

	Long getMaxSequenceNumber(long satelliteId);

	HexFrame getLatestFC2();
}
