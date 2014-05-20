package uk.org.funcube.fcdw.server.shared;

import java.util.ArrayList;
import java.util.List;

public class RealTimeInfo {
	
	List<ValMinMax> epsValues = new ArrayList<ValMinMax>();
	List<ValMinMax> asibValues = new ArrayList<ValMinMax>();
	List<ValMinMax> rfValues = new ArrayList<ValMinMax>();
	List<ValMinMax> paValues = new ArrayList<ValMinMax>();
	List<ValMinMax> antsValues = new ArrayList<ValMinMax>();
	List<StringPair> swValues = new ArrayList<StringPair>();
	private Long sequenceNumber;
	private String lastUpdated;
	private List<String> siteList;
	private String minmaxReset;
	private String latitude;
	private String longitude;
	private String packetCount;
	
	public RealTimeInfo(Long sequenceNumber, String lastUpdated, List<ValMinMax> epsValues, List<ValMinMax> asibValues,
			List<ValMinMax> rfValues, List<ValMinMax> paValues,
			List<ValMinMax> antsValues, List<StringPair> swValues,
			List<String>siteList, String minmaxReset,
			String latitude, String longitude, String packetCount) {
		super();
		this.sequenceNumber = sequenceNumber;
		this.lastUpdated = lastUpdated;
		this.epsValues = epsValues;
		this.asibValues = asibValues;
		this.rfValues = rfValues;
		this.paValues = paValues;
		this.antsValues = antsValues;
		this.swValues = swValues;
		this.siteList = siteList;
		this.minmaxReset = minmaxReset;
		this.latitude = latitude;
		this.longitude = longitude;
		this.packetCount = packetCount;
	}

	public RealTimeInfo() {
	}

	public final List<ValMinMax> getEpsValues() {
		return epsValues;
	}

	public final List<ValMinMax> getAsibValues() {
		return asibValues;
	}

	public final List<ValMinMax> getRfValues() {
		return rfValues;
	}

	public final List<ValMinMax> getPaValues() {
		return paValues;
	}

	public final List<ValMinMax> getAntsValues() {
		return antsValues;
	}

	public final List<StringPair> getSwValues() {
		return swValues;
	}

	public final Long getSequenceNumber() {
		return sequenceNumber;
	}

	public final String getLastUpdated() {
		return lastUpdated;
	}

	public final List<String> getSiteList() {
		return siteList;
	}

	public final String getMinmaxReset() {
		return minmaxReset;
	}

	public final String getLatitude() {
		return latitude;
	}

	public final String getLongitude() {
		return longitude;
	}

	public final String getPacketCount() {
		return packetCount;
	}

}
