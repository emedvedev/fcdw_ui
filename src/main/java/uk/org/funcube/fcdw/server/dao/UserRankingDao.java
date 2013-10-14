package uk.org.funcube.fcdw.server.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import uk.org.funcube.fcdw.server.model.UserRanking;

public interface UserRankingDao extends DataAccessObject<UserRanking, Long> {

	@Cacheable(value = { "userRanking" })
	List<UserRanking> findBySatelliteId(Long satelliteId);

}
