package uk.org.funcube.fcdw.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserRanking")
public class UserRankingEntity implements UserRanking {

	@Id
	private String site;
	private long number;
	private long satelliteId;

	public UserRankingEntity() {

	}

	@Override
	public Long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	@Override
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public final long getSatelliteId() {
		return satelliteId;
	}

	public final void setSatelliteId(long satelliteId) {
		this.satelliteId = satelliteId;
	}

}
