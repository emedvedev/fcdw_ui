// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FitterMessage")
public class FitterMessageEntity implements FitterMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String messageText;

	private Timestamp lastReceived;

	private Timestamp createdDate;

	private Long satelliteId;

	private Boolean debug;

	private Boolean display;
	
	private String slot;

	public FitterMessageEntity() {
	}

	public FitterMessageEntity(String theMessageText, Timestamp lastReceived, Long satelliteId, Boolean debug,
			String slot, Timestamp createdDate) {
		super();
		this.messageText = theMessageText;
		this.lastReceived = lastReceived;
		this.satelliteId = satelliteId;
		this.debug = debug;
		this.slot = slot;
		this.createdDate = createdDate;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	@Override
	public final String getMessageText() {
		return messageText;
	}

	public final void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public final Timestamp getLastReceived() {
		return lastReceived;
	}

	@Override
	public final void setLastReceived(Timestamp lastReceived) {
		this.lastReceived = lastReceived;
	}

	public final Long getSatelliteId() {
		return satelliteId;
	}

	public final void setSatelliteId(Long satelliteId) {
		this.satelliteId = satelliteId;
	}

	public final Boolean getDebug() {
		return debug;
	}

	public final void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public final String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public final Timestamp getCreatedDate() {
		return createdDate;
	}

	public final void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
