package util;

public class SegregationData extends XMLData {
	private int threshold;
	
	public SegregationData(int numRows, int numColumns) {
		super(numRows, numColumns);
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public void setThreshold(int newThreshold) {
		threshold = newThreshold;
	}
}
