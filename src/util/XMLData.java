package util;

public abstract class XMLData {
	private int[][] cells;
	private String title;
	private int rows;
	private int columns;
	
	public XMLData(int numRows, int numColumns) {
		rows = numRows;
		columns = numColumns;
		cells = new int[rows][columns];
	}
	
	// Get methods
	public int[][] getCells() {
		return cells;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	// Set methods
	public void setCells(int[][] parsedCells) {
		cells = parsedCells;
	}
	
	public void setTitle(String parsedTitle) {
		title = parsedTitle;
	}
}