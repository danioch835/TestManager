package classes;

import java.util.ArrayList;

public class TestResult {
	
	private int resultIndex;
	private DataReader dataReader;
	private DataWriter dataWriter;
	private Player player;
	private Test test;
	
	public TestResult(DataReader dataReader, DataWriter dataWriter, int resultIndex) {
		this.resultIndex = resultIndex;
		this.dataReader = dataReader;
		this.dataWriter = dataWriter;
		this.resultIndex = resultIndex;
		loadTestResult();
	}
	
	public void loadTestResult() {
		dataReader.setResultIndex(resultIndex);
		player = dataReader.getPlayer();
		test = dataReader.getTest();
	}
	
	public void saveTestResult() {
		dataWriter.saveTestResult(player, test);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Test getTest() {
		return test;
	}
	
	public ArrayList<String> getTestCategoriesNames() {
		ArrayList<String> testsCategories = test.getTestsCategoriesNames();
		return testsCategories;
	}
	
	public ArrayList<String> getCategoryExercisesNames(String categoryName) {
		ArrayList<String> exercisesNames = new ArrayList<String>();
		ArrayList<String> categoriessNames = getTestCategoriesNames();
	
		if(categoriessNames.contains(categoryName)) {
			ArrayList<TestCategory> testCategories = getTestCategories();
			for(TestCategory category : testCategories) {
				if(category.getCategoryName().equals(categoryName)) {
					exercisesNames = category.getExercisesNames();
				}
			}
		}
		return exercisesNames;
	}
	
	public ArrayList<String> getExerciseResultsNames(String categoryName, String exerciseName) {
		ArrayList<String> resultsNames = new ArrayList<String>();
		ArrayList<Exercise> categoryExercises = getCategoryExercises(categoryName);
		
		for(Exercise exercise : categoryExercises) {
			if(exercise.getExerciseName().equals(exerciseName)) {
				resultsNames = exercise.getResultsNames();
			}
		}
		return resultsNames;
	}
	
	public Result getExerciseResult(String categoryName, String exerciseName, String resultName) {
		Result exerciseResult = new Result("");
		ArrayList<Result> results = getExerciseResults(categoryName, exerciseName);
		for(Result result : results) {
			if(result.getResultName().equals(resultName)) {
				exerciseResult = result;
			}
		}
		return exerciseResult;
	}
	
	private ArrayList<TestCategory> getTestCategories() {
		return test.getCategories();
	}
	
	private ArrayList<Exercise> getCategoryExercises(String categoryName) {
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		ArrayList<TestCategory> testCategories = getTestCategories();
			
		for(TestCategory category : testCategories) {
			if(category.getCategoryName().equals(categoryName)) {
				exercises = category.getExercises();
			}
		}
		return exercises;
	}
	
	private ArrayList<Result> getExerciseResults(String categoryName, String exerciseName) {
		ArrayList<Result> results = new ArrayList<Result>();
		ArrayList<Exercise> categoryExercises = getCategoryExercises(categoryName);
		
		for(Exercise exercise : categoryExercises) {
			if(exercise.getExerciseName().equals(exerciseName)) {
				results = exercise.getResults();
			}
		}
		return results;
	}
	
}