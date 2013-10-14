package uk.org.funcube.fcdw.server.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import uk.org.funcube.fcdw.server.model.FitterMessage;

public interface FitterMessageDao extends DataAccessObject<FitterMessage, Long> {

	@Cacheable(value = { "fitterMessage" })
	List<FitterMessage> findBySatelliteId(Long satelliteId, Boolean b);

	FitterMessage findBySatelliteIdAndMessageText(Long satelliteId, String messageText, Boolean isDebug);

	FitterMessage saveOrUpdate(FitterMessage fitterMessage);

	FitterMessage getCctDebug(long satelliteId);
}
