package uk.org.funcube.fcdw.server.model;

import java.util.Date;

public interface FitterMessage {

	void setLastReceived(Date lastReceived);

	String getMessageText();

	Date getLastReceived();

	void setDisplay(Boolean display);

}
