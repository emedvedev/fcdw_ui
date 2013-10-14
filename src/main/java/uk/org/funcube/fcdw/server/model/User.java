package uk.org.funcube.fcdw.server.model;

import java.util.List;

public interface User {
	
	String getUsername();
	String getPassword();
	String getLatitude();
	String getLongitude();
	String getSiteId();
	boolean isEnabled();
	boolean isAdmin();
	boolean isExpired();
	boolean isLocked();
	boolean isCredentialsExpired();
	String getAuthKey();
	void setEnabled(boolean state);
	void setPassword(String hashPassword);
	void addFrame(HexFrameEntity hexFrame);
	List<HexFrameEntity> getFrames();
	void setFrames(List<HexFrameEntity> hexFrames);
	void setId(Long i);
	Long getId();
}
