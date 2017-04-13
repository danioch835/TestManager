package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import testResultClasses.ExerciseCategory;
import testResultClasses.Player;
import testResultClasses.PlayerResult;
import testResultClasses.TestResult;

public class TestResultTest {

	@Test
	public void setPlayerTest() {
		Player player = new Player("Name", "Lastname");
		TestResult result = new TestResult();
		result.setPlayer(player);
		
		assertEquals("Name", result.getPlayer().getName());
		assertEquals("Lastname", result.getPlayer().getLastname());
		
	}
	
	@Test
	public void addCategoryTest() {
		ExerciseCategory category1 = new ExerciseCategory("category1");
		ExerciseCategory category2 = new ExerciseCategory("category2");
		TestResult result = new TestResult();
		result.addCategory(category1);
		result.addCategory(category2);
		
		assertEquals(2, result.getNumberOfCategories());
		
	}
	
	@Test
	public void addResulToCategoryTest() {
		ExerciseCategory category1 = new ExerciseCategory("category1");
		PlayerResult result1 = new PlayerResult();
		category1.addResult("exercise1", result1);
		TestResult result = new TestResult();
		result.addCategory(category1);
		
		assertEquals(1, result.getCategoryResults("category1").getNumberOfExercises());
	}

	@Test
	public void setDateTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date actualTestDate = null;
		Date lastTestDate = null;
		try {
			actualTestDate = sdf.parse("05.05.2016");
			lastTestDate = sdf.parse("05.05.2015");
		} catch (ParseException e) {
			e.printStackTrace();
			assertFalse("Can not parse data", true);
		}
		TestResult result = new TestResult();
		result.setActualTestDate(actualTestDate);
		result.setLastTestDate(lastTestDate);
		
		assertEquals(actualTestDate, result.getActualTestDate());
		assertEquals(lastTestDate, result.getLastTestDate());
	}

}
