package uk.org.funcube.fcdw.server.shared;


public class XAxis {
	
	private int min;
	private int max = 0;

	public XAxis() {
		// TODO Auto-generated constructor stub
	}

	public void setMin(int value) {
		min = value;
	}

	public final int getMin() {
		return min;
	}

	public final int getMax() {
		return max;
	}
}