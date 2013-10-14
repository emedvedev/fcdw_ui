// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails, User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String latitude;
	@Column
	private String longitude;
	@Column
	private String siteId;
	@Column
	private boolean enabled;
	@Column
	private boolean admin;
	@Column
	private boolean expired;
	@Column
	private boolean locked;
	@Column
	private boolean credentialsExpired;
	@Column
	private String authKey;
	@Column
	private boolean emailSent;

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private List<HexFrameEntity> hexFrames;

	public UserEntity() {
		hexFrames = new ArrayList<HexFrameEntity>();
	}

	public UserEntity(final String username, final String password, final String latitude, final String longitude, final String siteId,
			final boolean enabled, final boolean admin, final boolean expired, final boolean locked, final boolean credentialsExpired,
			final String authKey, final boolean emailSent) {
		this.username = username;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.siteId = siteId;
		this.enabled = enabled;
		this.admin = admin;
		this.expired = expired;
		this.locked = locked;
		this.credentialsExpired = credentialsExpired;
		this.authKey = authKey;
		this.setEmailSent(emailSent);

		hexFrames = new ArrayList<HexFrameEntity>();
	}

	public UserEntity(String username2, String password2, List<GrantedAuthority> perms) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public final String getUsername() {
		return username;
	}

	@Override
	public final String getPassword() {
		return password;
	}

	@Override
	public final String getLatitude() {
		return latitude;
	}

	@Override
	public final String getLongitude() {
		return longitude;
	}

	@Override
	public final String getSiteId() {
		return siteId;
	}

	@Override
	public final boolean isEnabled() {
		return enabled;
	}

	@Override
	public final boolean isAdmin() {
		return admin;
	}

	@Override
	public final boolean isExpired() {
		return expired;
	}

	@Override
	public final boolean isLocked() {
		return locked;
	}

	@Override
	public final boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	@Override
	public final String getAuthKey() {
		return authKey;
	}

	@Override
	public void setPassword(final String hashPassword) {
		this.password = hashPassword;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		if (isAdmin()) {
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authList;

	}

	@Override
	public boolean isAccountNonExpired() {
		return !expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public void setEnabled(final boolean state) {
		this.enabled = state;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	@Override
	public void addFrame(final HexFrameEntity hexFrame) {
		if (!getFrames().contains(hexFrame)) {
			getFrames().add(hexFrame);
		}
		if (!hexFrame.getUsers().contains(this)) {
			hexFrame.getUsers().add(this);
		}
	}

	@Override
	public List<HexFrameEntity> getFrames() {
		return hexFrames;
	}

	@Override
	public void setFrames(final List<HexFrameEntity> frames) {
		this.hexFrames = frames;
	}

}
