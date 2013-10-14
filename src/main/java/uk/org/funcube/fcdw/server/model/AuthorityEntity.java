package uk.org.funcube.fcdw.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String username;
	@Column
	private String authority;
	
	public AuthorityEntity() {
	}

	public AuthorityEntity(final Long id, final String username, final String authority) {
		this.id = id;
		this.username = username;
		this.authority = authority;
	}

	public AuthorityEntity(final String username, final String authority) {
		this.username = username;
		this.authority = authority;
	}

	public final Long getId() {
		return id;
	}

	public final String getUsername() {
		return username;
	}

	public final String getAuthority() {
		return authority;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	public final void setUsername(final String username) {
		this.username = username;
	}

	public final void setAuthority(final String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AuthorityEntity other = (AuthorityEntity) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
