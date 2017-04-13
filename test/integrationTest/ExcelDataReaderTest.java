package integrationTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

import dataManipulationClasses.ExcelDataReader;
import testResultClasses.ExerciseCategory;
import testResultClasses.Player;

public class ExcelDataReaderTest {

	private String excelFilePath = "testResources/testResults.xlsx";
	
	@Test
	public void loadPlayersTest() {
		ExcelDataReader reader = new ExcelDataReader(excelFilePath);
		ArrayList<Player> players = reader.loadPlayers();
		
		assertEquals(28, players.size());
	}
	
	@Test
	public void loadActualTestDateTest() {
		ExcelDataReader reader = new ExcelDataReader(excelFilePath);
		SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
		Date expectedActualTestDate = null;
		try {
			expectedActualTestDate = sdf.parse("02.2017");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(expectedActualTestDate, reader.getActualTestDate());
	}
	
	@Test
	public void loadLastTestDateTest() {
		ExcelDataReader reader = new ExcelDataReader(excelFilePath);
		SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
		Date expectedLastTestDate = null;
		try {
			expectedLastTestDate = sdf.parse("09.2016");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertEquals(expectedLastTestDate, reader.getLastTestDate());
	}
	
	@Test
	public void loadExercisesStartColumnsTest() {
		ExcelDataReader reader = new ExcelDataReader(excelFilePath);
		LinkedHashMap<String, Integer> exercisesColumns = reader.loadExercisesStartColumns(0);
		
		assertEquals(3, exercisesColumns.size());
		assertEquals(new Integer(4), exercisesColumns.get("Bieg 5m [s]"));
		assertEquals(new Integer(6), exercisesColumns.get("Bieg 10m [s]"));
		assertEquals(new Integer(8), exercisesColumns.get("Bieg 20m [s]"));
	}
	
	@Test
	public void loadCategoryResultTest() {
		ExcelDataReader reader = new ExcelDataReader(excelFilePath);
		ArrayList<ExerciseCategory> categoryResult = reader.loadCategoryResults(0);
		ExerciseCategory firstResult = categoryResult.get(0);
		String categoryName = firstResult.getName();
		int exercisesCount = firstResult.getNumberOfExercises();
		
		assertEquals(28, categoryResult.size());
		assertEquals(3, exercisesCount);
		assertEquals("SZYBKOŒÆ", categoryName);
	}

}
