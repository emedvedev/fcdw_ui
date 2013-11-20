package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class WodJson {
	
	private LinkedList series = new LinkedList();
	
	public WodJson() {
		
	}
	
	public LinkedList getSeries() {
		return series;
	}
	
	public void addElement(WodElement element) {
		series.add(element);
	}

}
