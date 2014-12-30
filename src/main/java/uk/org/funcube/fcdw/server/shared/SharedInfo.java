package uk.org.funcube.fcdw.server.shared;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SharedInfo {

	protected List<String> siteList;
	protected String satelliteMode;
	protected String transponderState;
	protected Integer valid;

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

	public final String getTransponderState() {
		return transponderState;
	}

	public final void setTransponderState(String transponderState) {
		this.transponderState = transponderState;
	}

	public final Integer getValid() {
		return valid;
	}

	public final void setValid(Integer valid) {
		this.valid = valid;
	}

}