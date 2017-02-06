package util;

public class FireData extends XMLData {
	private int probCatch;
	
	public FireData(int numRows, int numColumns) {
		super(numRows, numColumns);
	}
	
	public int getProbCatch() {
		return probCatch;
	}
	
	public void setProbCatch(int newProbCatch) {
		probCatch = newProbCatch;
	}
}
