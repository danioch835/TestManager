package test;

import static org.junit.Assert.*;

import org.junit.Test;

import testResultClasses.ExerciseCategory;
import testResultClasses.PlayerResult;

public class ExerciseCategoryTest {

	@Test
	public void test() {
		ExerciseCategory category = new ExerciseCategory("category1");
		PlayerResult result1 = new PlayerResult();
		PlayerResult result2 = new PlayerResult();
		PlayerResult result3 = new PlayerResult();
		PlayerResult result4 = new PlayerResult();
		
		category.addResult("exercise1", result1);
		category.addResult("exercise2", result2);
		category.addResult("exercise3", result3);
		category.addResult("exercise4", result4);
		
		assertEquals("category1", category.getName());
		assertEquals(4, category.getNumberOfExercises());
		
	}

}
