package uk.org.funcube.fcdw.server.shared;

import java.util.LinkedList;

public class DataElement {
	
	private String name;
	
	@SuppressWarnings("rawtypes")
	private LinkedList data = new LinkedList();

	public DataElement(String name) {
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
	
	@SuppressWarnings("unchecked")
	public void addDatum(Long value1, Double value2 ) {
		LinkedList dataPair = new LinkedList();
		dataPair.add(value1);
		dataPair.add(value2);
		data.add(dataPair);
	}

}
