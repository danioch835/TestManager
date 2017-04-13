package dataManipulationClasses;

public class Index {
	private int column;
	private int row;
	
	public Index(int column, int row) {
		this.column = column;
		this.row = row;
	};
	
	public int getColumnIndex() {
		return column;
	}
	
	public int getRowIndex() {
		return row;
	}
}
