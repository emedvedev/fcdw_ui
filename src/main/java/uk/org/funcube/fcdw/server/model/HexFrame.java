package uk.org.funcube.fcdw.server.model;

import java.util.Date;
import java.util.Set;

public interface HexFrame {

	Set<UserEntity> getUsers();

	Long getFrameType();

	Long getSatelliteId();

	String getHexString();

	Date getCreatedDate();

	Long getSequenceNumber();

	void setFitterProcessed(boolean state);

	void setRealtimeProcessed(boolean b);

}
