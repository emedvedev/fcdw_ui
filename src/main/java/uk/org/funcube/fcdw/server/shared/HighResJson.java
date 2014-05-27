package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;

import org.codehaus.jackson.annotate.JsonProperty;

public class HighResJson extends SharedInfo {
	
	private LinkedList series = new LinkedList();
	private XAxis xAxis = new XAxis();
	
	public HighResJson() {
		xAxis.setMax(0);
	}
	
	public LinkedList getSeries() {
		return series;
	}
	
	@JsonProperty(value="xAxis")
	public XAxis getXAxis() {
		return xAxis;
	}
	
	public void addElement(DataElement element) {
		series.add(element);
	}
	
	public void setMinX(int value) {
		xAxis.setMin(value);
	}

}
