package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classes.DataReader;
import classes.ExcelDataReader;
import classes.Player;
import classes.TestResult;

public class TestResultTest {

	@Test
	public void playerTest() {
		DataReader dataReader = new ExcelDataReader("src/test/resources/test.xls");
		TestResult testResult = new TestResult(dataReader,1);
		Player player = testResult.getPlayer();
		assertEquals("Daniel", player.getName());
		assertEquals("Ochojski", player.getLastname());
	}
	
	@Test
	public void testCaseTest() {
		DataReader dataReader = new ExcelDataReader("src/test/resources/test.xls");
		TestResult testResult = new TestResult(dataReader,0);
		classes.Test test = testResult.getTest();
		ArrayList<String> categoriesNames = test.getTestsCategoriesNames();
		assertEquals("TEST SPRAWNOŒÆ SPECJALNA", categoriesNames.get(0));
		assertEquals("TEST SPRAWNOŒÆ OGÓLNA", categoriesNames.get(1));
	}
	
	@Test
	public void ExercisesNamesTest() {
		DataReader dataReader = new ExcelDataReader("src/test/resources/test.xls");
		TestResult testResult = new TestResult(dataReader,0);
		classes.Test test = testResult.getTest();
		ArrayList<String> categoriesNames = test.getTestsCategoriesNames();
		ArrayList<String> exercisesNames = test.getCategoryExercises(categoriesNames.get(0));
		assertEquals("¯ONGLERKA", exercisesNames.get(0));
		assertEquals("SLALOM [s]", exercisesNames.get(1));
	}

}
