package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class WodJson extends SharedInfo {
	
	private LinkedList series = new LinkedList();
	
	public WodJson() {
		
	}
	
	public LinkedList getSeries() {
		return series;
	}
	
	public void addElement(DataElement element) {
		series.add(element);
	}

}
