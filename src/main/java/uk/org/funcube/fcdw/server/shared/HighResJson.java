package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class HighResJson {
	
	private LinkedList series = new LinkedList();
	
	public HighResJson() {
		
	}
	
	public LinkedList getSeries() {
		return series;
	}
	
	public void addElement(DataElement element) {
		series.add(element);
	}

}
