package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class WodElement {
	
	private String name;
	
	@SuppressWarnings("rawtypes")
	private LinkedList data = new LinkedList();

	public WodElement(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@SuppressWarnings("rawtypes")
	public LinkedList getData() {
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public void addDatum(Double value) {
		data.add(value);
	}
	
	@SuppressWarnings("unchecked")
	public void addDatum(Long value) {
		data.add(value);
	}

}
