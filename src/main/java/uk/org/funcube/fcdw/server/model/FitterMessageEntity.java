package uk.org.funcube.fcdw.server.model;

import java.util.Date;

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

	private Date lastReceived;

	private Long satelliteId;

	private Boolean debug;

	private Boolean display;

	public FitterMessageEntity() {
	}

	public FitterMessageEntity(String theMessageText, Date lastReceived, Long satelliteId, Boolean debug) {
		super();
		this.messageText = theMessageText;
		this.lastReceived = lastReceived;
		this.satelliteId = satelliteId;
		this.debug = debug;
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
	public final Date getLastReceived() {
		return lastReceived;
	}

	@Override
	public final void setLastReceived(Date lastReceived) {
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

}
