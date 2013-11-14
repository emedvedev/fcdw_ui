// FUNcube Data Warehouse
// Copyright 2013 (c) David A.Johnson, G4DPZ, AMSAT-UK
// This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
// To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter
// to Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.

package uk.org.funcube.fcdw.web.viewmodel;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import uk.org.funcube.fcdw.web.validator.EmailNotDuplicate;
import uk.org.funcube.fcdw.web.validator.FieldMatch;
import uk.org.funcube.fcdw.web.validator.LatLong;
import uk.org.funcube.fcdw.web.validator.SiteName;
import uk.org.funcube.fcdw.web.validator.SiteNameNotDuplicate;

@FieldMatch.List({
	@FieldMatch(first = "password1", second = "password2", message = "The password fields must match"),
	@FieldMatch(first = "email1", second = "email2", message = "The email fields must match")
})
public class RegisterUserRequest {

	@SiteName
	@Size(min = 3, max = 30)
	@SiteNameNotDuplicate(message = "Already registered")
	private String siteName;

	@LatLong(message = "Incorrect format")
	@Range(min = -90, max = 90)
	private String latitude;

	@LatLong(message = "Incorrect format")
	@Range(min = -360, max = 360)
	private String longitude;

	@NotEmpty
	@Email
	@EmailNotDuplicate(message = "Already registered")
	private String email1;

	@NotEmpty
	@Email
	@EmailNotDuplicate(message = "Already registered")
	private String email2;

	@Size(min = 8, max = 20)
	private String password1;

	@Size(min = 8, max = 20)
	private String password2;

	private Boolean detailsEmailed;

	public RegisterUserRequest() {

	}

	public RegisterUserRequest(String siteName, String latitude, String longitude, String email1, String email2,
			String password1, String password2) {
		this.siteName = siteName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.email1 = email1;
		this.email2 = email2;
		this.password1 = password1;
		this.password2 = password2;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Boolean getDetailsEmailed() {
		return detailsEmailed;
	}

	public void setDetailsEmailed(Boolean detailsEmailed) {
		this.detailsEmailed = detailsEmailed;
	}

}
