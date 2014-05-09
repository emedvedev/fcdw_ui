// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.model;

import java.util.List;
import java.util.Set;

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
	Set<HexFrameEntity> getFrames();
	void setFrames(Set<HexFrameEntity> hexFrames);
	void setId(Long i);
	Long getId();
}
