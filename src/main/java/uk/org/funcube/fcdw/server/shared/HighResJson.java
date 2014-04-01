package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class HighResJson {
	
	private LinkedList series = new LinkedList();
	private XAxis xAxis = new XAxis();
	
	public HighResJson() {
		
	}
	
	public LinkedList getSeries() {
		return series;
	}
	
	public XAxis getXAxis() {
		return xAxis;
	}
	
	public void setMinX(final int value) {
		xAxis.setMin(value);
	}
	
	public void addElement(DataElement element) {
		series.add(element);
	}

}
