package uk.org.funcube.fcdw.server.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SatelliteStatus")
public class SatelliteStatusEntity implements SatelliteStatus {
	
	@Id
	private Long satelliteId;
	
	private Long sequenceNumber;
	private Boolean eclipseModeForced;
	private Boolean eclipsed;
	private Timestamp lastUpdated;
	private Double eclipseDepth;

	private Boolean eclipseSwitch;
	
	public SatelliteStatusEntity() {
	}

	public final Long getSatelliteId() {
		return satelliteId;
	}

	public final void setSatelliteId(Long satelliteId) {
		this.satelliteId = satelliteId;
	}

	public final Long getSequenceNumber() {
		return sequenceNumber;
	}

	public final void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public final Boolean isEclipseModeForced() {
		return eclipseModeForced;
	}

	public final void setEclipseModeForced(Boolean eclipseModeForced) {
		this.eclipseModeForced = eclipseModeForced;
	}

	public final Boolean isEclipsed() {
		return eclipsed;
	}

	public final void setEclipsed(Boolean eclipsed) {
		this.eclipsed = eclipsed;
	}

	public final Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public final void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public final Double getEclipseDepth() {
		return eclipseDepth;
	}

	public final void setEclipseDepth(Double eclipseDepth) {
		this.eclipseDepth = eclipseDepth;
	}

	public void setEclipseSwitch(Boolean eclipseSwitch) {
		this.eclipseSwitch = eclipseSwitch;
	}

	public Boolean getEclipseModeForced() {
		return eclipseModeForced;
	}

	public Boolean getEclipsed() {
		return eclipsed;
	}

	public Boolean isEclipseSwitch() {
		return eclipseSwitch;
	}
	
	@Transient
	public String getMode() {
		if (!isEclipseSwitch()) {
			if (!isEclipseModeForced()) {
				return "Forced Transponder";
			} else {
				return "Forced Amateur";
			}
		} else {
			if (!isEclipsed()) {
				return "Education";
			} else {
				return "Transponder";
			}
		}
	}

}
