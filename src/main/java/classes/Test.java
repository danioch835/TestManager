package classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Test {
	
	private ArrayList<TestCategory> testCategories;
	
	public Test() {
		testCategories = new ArrayList<TestCategory>();
	}
	
	public void addTestCategory(TestCategory testCategory) {
		testCategories.add(testCategory);
	}
	
	public int getNumberOfCategories() {
		return testCategories.size();
	}
	
	public ArrayList<TestCategory> getCategories() {
		return testCategories;
	}
	
	public ArrayList<String> getTestsCategoriesNames() {
		ArrayList<String> names = new ArrayList<String>();
		
		for(TestCategory test : testCategories) {
			names.add(test.getCategoryName());
		}
		return names;
	}
	
	public ArrayList<String> getCategoryExercises(String categoryName) {
		ArrayList<String> exercisesNames = new ArrayList<String>();
		TestCategory testCategory = null;
		for(TestCategory test : testCategories) {
			if(test.getCategoryName().equals(categoryName)) {
				testCategory = test;
			}
		}
		if(testCategory != null) {
			exercisesNames = testCategory.getExercisesNames();
		}
		return exercisesNames;
	}
	
}
