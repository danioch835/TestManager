package classes;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelDataReader implements DataReader {

	private Workbook workbook;
	private Sheet sheet;
	
	public ExcelDataReader(String fileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			workbook = new HSSFWorkbook(fileInputStream);
			fileInputStream.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setResultIndex(int resultIndex) {
		sheet = workbook.getSheetAt(resultIndex);
	}
	
	public int getNumberOfResults() {
		return workbook.getNumberOfSheets();
	}
	
	public Player getPlayer() {
		Player newPlayer = new Player();
		for(int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if(row != null) {
				for(Cell cell : row) {
					if(cell.getCellTypeEnum() == CellType.STRING) {
						if(cell.getStringCellValue().equals("Nazwisko")) {
							Cell nextCell = row.getCell(cell.getColumnIndex()+1);
							String lastname = nextCell.getStringCellValue();
							newPlayer.setLastname(lastname);
						} else if(cell.getStringCellValue().equals("Imiê") || cell.getStringCellValue().equals("Imie")) {
							Cell nextCell = row.getCell(cell.getColumnIndex()+1);
							String name = row.getCell(nextCell.getColumnIndex()).getStringCellValue();
							newPlayer.setName(name);
						}
					}
				}
			}
		}
		return newPlayer;
	}

	public Test getTest() {
		Test newTest = new Test();
		TestCategory newTestCategory = null;
		Exercise newExercise = null;
		Result newResult = null;
		
		Index testsBaseIndex = getTestsBaseIndex();
		Index categoriesBaseIndex = new Index(testsBaseIndex.getColumnIndex()+1, testsBaseIndex.getRowIndex());
		int lastCategoriesRow = getCategoriesEndRow(categoriesBaseIndex);
				
		for(int i = categoriesBaseIndex.getRowIndex(); i < lastCategoriesRow; i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(categoriesBaseIndex.getColumnIndex());
			
			if(isRowCategory(row, cell)) {
				addResult(newExercise, newResult);
				addExercise(newTestCategory, newExercise);
				addCategory(newTest, newTestCategory);
				newTestCategory = new TestCategory(cell.getStringCellValue());
				newExercise = null;
				newResult = null;
			} else if(isRowExercise(cell)) {
				addResult(newExercise, newResult);
				addExercise(newTestCategory, newExercise);
				newExercise = new Exercise(cell.getStringCellValue());
				Cell resultNameCell = row.getCell(cell.getColumnIndex()+1);
				Cell resultPlayerCell = row.getCell(cell.getColumnIndex()+2);
				Cell resultTeamCell = row.getCell(cell.getColumnIndex()+3);
				newResult = new Result(resultNameCell.getStringCellValue());
				newResult.setPlayerResult(resultPlayerCell.getNumericCellValue());
				newResult.setTeamAverageResult(resultTeamCell.getNumericCellValue());
			} else if(isRowResult(row, cell)) {
				addResult(newExercise, newResult);
				Cell resultNameCell = row.getCell(cell.getColumnIndex()+1);
				Cell resultPlayerCell = row.getCell(cell.getColumnIndex()+2);
				Cell resultTeamCell = row.getCell(cell.getColumnIndex()+3);
				newResult = new Result(resultNameCell.getStringCellValue());
				newResult.setPlayerResult(resultPlayerCell.getNumericCellValue());
				newResult.setTeamAverageResult(resultTeamCell.getNumericCellValue());
			}
		}
		
		addResult(newExercise, newResult);
		addExercise(newTestCategory, newExercise);
		addCategory(newTest, newTestCategory);
		
		return newTest;
	}
	
	private boolean isRowCategory(Row row, Cell cell) {
		int cellColumnIndex = cell.getColumnIndex();
		Cell resultCell = row.getCell(cellColumnIndex+1);
		return isCellEmpty(resultCell);
	}
	
	private boolean isRowExercise(Cell cell) {
		return !isCellEmpty(cell);
	}
	
	private boolean isRowResult(Row row, Cell cell) {
		Cell resultCell = row.getCell(cell.getColumnIndex()+1);
		return !isCellEmpty(resultCell);
	}
	
	private void addCategory(Test test, TestCategory testCategory) {
		if(testCategory != null) {
			test.addTestCategory(testCategory);
		}
	}
	
	private void addExercise(TestCategory testCategory, Exercise exercise) {
		if(exercise != null) {
			testCategory.addExercise(exercise);
		}
	}
	
	private void addResult(Exercise exercise, Result result) {
		if(result != null) {
			exercise.addResult(result);
		}
	}
	
	private int getCategoriesEndRow(Index categoriesBaseIndex) {
		int lastCategoriesRow = categoriesBaseIndex.getRowIndex();

		for(int i = categoriesBaseIndex.getRowIndex(); ;i++) {
			Row row = sheet.getRow(i);
			if(row == null) {
				break;
			} else {
				Cell cell = row.getCell(categoriesBaseIndex.getColumnIndex());
				if(isCellEmpty(cell)) {
					cell = row.getCell(categoriesBaseIndex.getColumnIndex()+1);
					if(isCellEmpty(cell)) {
						break;
					} else {
						lastCategoriesRow++;
					} 
				} else {
					lastCategoriesRow++;
				}
			}
		}
		return lastCategoriesRow;
	}
	
	private boolean isCellEmpty(Cell cell) {
		boolean isEmpty = false;
		if(cell == null) {
			isEmpty = true;
		} else {
			switch(cell.getCellTypeEnum()) {
				case STRING:
					if(cell.getStringCellValue().equals(" ")) {
						isEmpty = true;
					} else {
						isEmpty = false;
					}
					break;
				case BLANK:
					isEmpty = true;
					break;
				case NUMERIC:
					if(cell.getNumericCellValue() != 0) {
						isEmpty = false;
					} else {
						isEmpty = true;
					}
					
					break;
				case _NONE:
					isEmpty = true;
					break;
					default:
			}
		}
		return isEmpty;
	}
	
	private Index getTestsBaseIndex() {
		Index newIndex = null;
		
		for(Row row : sheet) {
			for(Cell cell : row) {
				if(cell.getCellTypeEnum() == CellType.STRING) {
					if(cell.getStringCellValue().equals("TESTY")) {
						newIndex = new Index(cell.getColumnIndex(), cell.getRowIndex());
					}
				}
			}
		}
		return newIndex;
	}
}
