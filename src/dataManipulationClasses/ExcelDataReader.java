package dataManipulationClasses;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testResultClasses.ExerciseCategory;
import testResultClasses.Player;
import testResultClasses.PlayerResult;

public class ExcelDataReader implements DataReader {

	private Workbook workbook;
	private Sheet sheet;
	private XMLConfigurator xmlConfigurator;
	private LinkedHashMap<String, Integer> exercisesStartColumns;
	
	public ExcelDataReader(String fileName) {
		try {
			xmlConfigurator = new XMLConfigurator("testResources/TestFile.xml");
			xmlConfigurator.loadPositionsFromXML();
			FileInputStream fileInputStream = new FileInputStream(fileName);
			workbook = new XSSFWorkbook(fileInputStream);
			fileInputStream.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<ExerciseCategory> loadCategoryResults(int categoryIndex) {
		sheet = workbook.getSheetAt(categoryIndex);
		loadExercisesStartColumns(categoryIndex);
		ArrayList<ExerciseCategory> results = new ArrayList<ExerciseCategory>();
		DataPosition resultPosition = xmlConfigurator.getResultsPosition();
		
		for(int i=resultPosition.getRow()-1;;i++) {
			Row row = sheet.getRow(i);
			if(row == null || isCellEmpty(row.getCell(resultPosition.getColumn()-1))) {
				break;
			} else {
				ExerciseCategory categoryPlayerResult = loadCategoryResultFromRow(row);
				results.add(categoryPlayerResult);
			}	
		}
		return results;
	}
	
	private ExerciseCategory loadCategoryResultFromRow(Row row) {
		ExerciseCategory categoryResult = new ExerciseCategory(sheet.getSheetName());
		
		for(Map.Entry<String, Integer> pair : exercisesStartColumns.entrySet()) {
			String exerciseName = pair.getKey();
			int columnIndex = pair.getValue();
			
			Cell actualResultCell = row.getCell(columnIndex);
			Cell lastResultCell = row.getCell(columnIndex+1);
			double lastResult = 0;
			double actualResult = 0;
			if(actualResultCell.getCellTypeEnum() == CellType.NUMERIC || actualResultCell.getCellTypeEnum() == CellType.FORMULA) {
				actualResult = actualResultCell.getNumericCellValue();
			} else if(actualResultCell.getCellTypeEnum() == CellType.STRING) {
				if(actualResultCell.getStringCellValue().equals("-")) {
					actualResult = -1;
				}
			}
			if(lastResultCell.getCellTypeEnum() == CellType.NUMERIC || lastResultCell.getCellTypeEnum() == CellType.FORMULA) {
				lastResult = lastResultCell.getNumericCellValue();
			} else if(lastResultCell.getCellTypeEnum() == CellType.STRING) {
				if(lastResultCell.getStringCellValue().equals("-")) {
					lastResult = -1;
				}
			}
			
			PlayerResult playerResult = new PlayerResult();
			playerResult.setActualResult(actualResult);
			playerResult.setLastResult(lastResult);
			categoryResult.addResult(exerciseName, playerResult);
		}
		
		return categoryResult;
	}
	
	public ArrayList<Player> loadPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		DataPosition playerPosition = xmlConfigurator.getPlayersPosition();
		sheet = workbook.getSheetAt(0);
		int rowIndex = playerPosition.getRow()-1; 
		Row row = null;
		
		while((row = sheet.getRow(rowIndex)) != null) {
			String lastname = row.getCell(playerPosition.getColumn()-1).getStringCellValue();
			String name = row.getCell(playerPosition.getColumn()).getStringCellValue();
			String dateOfBirth = row.getCell(playerPosition.getColumn()+1).getStringCellValue();
			Player player = new Player(name, lastname);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				player.setDateOfBirth(sdf.parse(dateOfBirth));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			players.add(player);
			rowIndex++;
		}
		
		return players;
	}
	
	public int getNumberOfCategories() {
		return workbook.getNumberOfSheets();
	}
	
	public LinkedHashMap<String, Integer> loadExercisesStartColumns(int categoryIndex) {
		exercisesStartColumns = new LinkedHashMap<String, Integer>();
		sheet = workbook.getSheetAt(categoryIndex);
		DataPosition exercisesPosition = xmlConfigurator.getExercisesNamesPosition();
		Row exerciseRow = sheet.getRow(exercisesPosition.getRow()-1);
		
		int exerciseStartColumnIndex = exercisesPosition.getColumn()-1;
		String actualExerciseName = "";
		for(int i = exerciseStartColumnIndex;;i++) {
			Cell cell = exerciseRow.getCell(i);
			if(isCellEmpty(cell)) {
				break;
			} else {
				if(!cell.getStringCellValue().equals(actualExerciseName)) {
					actualExerciseName = cell.getStringCellValue();
					exercisesStartColumns.put(actualExerciseName, new Integer(cell.getColumnIndex()));
				}
			}
		}
		
		return exercisesStartColumns;
	}
	
	public Date getActualTestDate() {
		Date actualTestDate = null;
		DataPosition datesPosition = xmlConfigurator.getDatesPosition();
		sheet = workbook.getSheetAt(0);
		Row datesRow = sheet.getRow(datesPosition.getRow()-1);
		String date = datesRow.getCell(datesPosition.getColumn()).getStringCellValue();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
		try {
			actualTestDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return actualTestDate;
	}
	
	public Date getLastTestDate() {
		Date lastTestDate = null;
		DataPosition datesPosition = xmlConfigurator.getDatesPosition();
		sheet = workbook.getSheetAt(0);
		Row datesRow = sheet.getRow(datesPosition.getRow()-1);
		String date = datesRow.getCell(datesPosition.getColumn()-1).getStringCellValue();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
		try {
			lastTestDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lastTestDate;
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
				case _NONE:
					isEmpty = true;
					break;
					default:
			}
		}
		return isEmpty;
	}
}
