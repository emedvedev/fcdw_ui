package uk.org.funcube.fcdw.server.shared;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SharedInfo {

	protected List<String> siteList;
	protected String satelliteMode;

	public SharedInfo() {
		super();
	}

	@JsonProperty(value="siteList")
	public List<String> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<String> siteList) {
		this.siteList = siteList;
	}

	public String getSatelliteMode() {
		return satelliteMode;
	}

	public void setSatelliteMode(String satelliteMode) {
		this.satelliteMode = satelliteMode;
	}

}