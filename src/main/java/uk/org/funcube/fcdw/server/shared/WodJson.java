package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class WodJson extends SharedInfo {
	
	private LinkedList<DataElement> series = new LinkedList<DataElement>();
	
	public WodJson() {
		
	}
	
	public LinkedList<DataElement> getSeries() {
		return series;
	}
	
	public void addElement(DataElement element) {
		series.add(element);
	}

}
