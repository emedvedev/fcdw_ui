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
	private Boolean eclipseModeForced = false;
	private Boolean eclipsed = false;
	private Timestamp lastUpdated;
	private Double eclipseDepth;
	private Boolean eclipseSwitch = false;
	private Timestamp lastWodDump;

	private Timestamp lastResetNotification;
	
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
		if (isEclipseSwitch()) {
			return "Auto";
		} else {
			return "Manual";
		}
	}
	
	@Transient
	public String getTransponderState() {
		if (!isEclipseSwitch()) {
			if (!isEclipseModeForced()) {
				return "Off";
			} else {
				return "On";
			}
		} else {
			if (!isEclipseModeForced()) {
				if (!isEclipsed()) {
					return "Off";
				} else {
					return "On";
				}
			} else {
				if (!isEclipsed()) {
					return "Off";
				} else {
					return "On";
				}
			}
		}
	}
	
	public final Timestamp getLastWodDump() {
		return lastWodDump;
	}

	public final void setLastWodDump(Timestamp lastWodDump) {
		this.lastWodDump = lastWodDump;
	}

	public final Timestamp getLastResetNotification() {
		return lastResetNotification;
	}

	public final void setLastResetNotification(Timestamp lastResetNotification) {
		this.lastResetNotification = lastResetNotification;
	}

}
