package uk.org.funcube.fcdw.server.shared;

import java.util.ArrayList;
import java.util.List;

public class WodData {
	
	String name;
	List<String> data = new ArrayList<String>();

	/**
	 * @param name
	 */
	public WodData(String name) {
		super();
		this.name = name;
	}

	public final String getName() {
		return name;
	}
	
	

}
