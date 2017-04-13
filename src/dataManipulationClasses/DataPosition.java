package dataManipulationClasses;

public class DataPosition {

	private int row;
	private int column;
	private int rowLength;
	private int columnLength;
	
	public DataPosition() {
		rowLength = 1;
		columnLength = 1;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setRowLenth(int rowLength) {
		this.rowLength = rowLength;
	}
	
	public int getRowLength() {
		return rowLength;
	}
	
	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}
	
	public int getColumnLength() {
		return columnLength;
	}
}
