package uk.org.funcube.fcdw.server.shared;

import java.util.ArrayList;
import java.util.List;

public class RealTimeInfo {
	
	List<ValMinMax> epsValues = new ArrayList<ValMinMax>();
	List<ValMinMax> asibValues = new ArrayList<ValMinMax>();
	List<ValMinMax> rfValues = new ArrayList<ValMinMax>();
	List<ValMinMax> paValues = new ArrayList<ValMinMax>();
	List<StringPair> antsValues = new ArrayList<StringPair>();
	List<StringPair> swValues = new ArrayList<StringPair>();
	private Long sequenceNumber;
	
	public RealTimeInfo(Long sequenceNumber, List<ValMinMax> epsValues, List<ValMinMax> asibValues,
			List<ValMinMax> rfValues, List<ValMinMax> paValues,
			List<StringPair> antsValues, List<StringPair> swValues) {
		super();
		this.sequenceNumber = sequenceNumber;
		this.epsValues = epsValues;
		this.asibValues = asibValues;
		this.rfValues = rfValues;
		this.paValues = paValues;
		this.antsValues = antsValues;
		this.swValues = swValues;
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

	public final List<StringPair> getAntsValues() {
		return antsValues;
	}

	public final List<StringPair> getSwValues() {
		return swValues;
	}

	public final Long getSequenceNumber() {
		return sequenceNumber;
	}

}
