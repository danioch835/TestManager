package gui;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JProgressBar;

import dataManipulationClasses.DataReader;
import dataManipulationClasses.DataWriter;
import dataManipulationClasses.ExcelDataReader;
import dataManipulationClasses.PDFDataWriter;
import testResultClasses.ExerciseCategory;
import testResultClasses.Player;
import testResultClasses.TestResult;

public class Application {

	private ArrayList<String> generatedFilesNames;
	private ArrayList<TestResult> testResults;
	private ArrayList<Player> players;
	private String excelFilePath;
	
	public Application() {
		generatedFilesNames = new ArrayList<String>();
	}
	
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	
	public ArrayList<String> getGeneratedFilesNames() {
		return generatedFilesNames;
	}
	
	public TestResult getPlayerResults(int index) {
		return testResults.get(index);
	}
	
	public Player getPlayer(int index) {
		return players.get(index);
	}
	
	public int getNumberOfPlayers() {
		return players.size();
	}
	
	public void run(JProgressBar progressBar) {
		testResults = new ArrayList<TestResult>();
		players = new ArrayList<Player>();
		DataReader dataReader = new ExcelDataReader(excelFilePath);
		DataWriter dataWriter = new PDFDataWriter();
		
		Date actualTestDate = dataReader.getActualTestDate();
		Date lastTestDate = dataReader.getLastTestDate();
		int numberOfCategories = dataReader.getNumberOfCategories();
		
		players = dataReader.loadPlayers();
		
		progressBar.setValue(10);
		
		for(Player player : players) {
			testResults.add(new TestResult());
		}
		
		progressBar.setValue(20);
		
		for(int i = 0; i < numberOfCategories; i++) {
			ArrayList<ExerciseCategory> categoryResults = dataReader.loadCategoryResults(i);
			for(int j=0; j < categoryResults.size(); j++) {
				TestResult actualTestResult = testResults.get(j);
				actualTestResult.addCategory(categoryResults.get(j));
				actualTestResult.setPlayer(players.get(j));
				actualTestResult.setActualTestDate(actualTestDate);
				actualTestResult.setLastTestDate(lastTestDate);
			}
			progressBar.setValue(20+i*5);
		}
		
		dataWriter.saveTestResult(testResults.get(0));
		
		progressBar.setValue(100);
		
		/*for(TestResult testPlayerResult : testResults) {
			dataWriter.saveTestResult(testPlayerResult);
		}*/
		
		/*TestResult exampleResult = testResults.get(0);
		
		ArrayList<String> categories = exampleResult.getTestCategoriesNames();
		for(String name : categories) {
			System.out.println(name);
			ArrayList<String> exercises = exampleResult.getCategoryExercisesNames(name);
			for(String exerciseName : exercises) {
				System.out.println(exerciseName);
				ArrayList<String> results = exampleResult.getExerciseResultsNames(name, exerciseName);
				for(String resultsNames : results) {
					System.out.println(resultsNames);
				}
			}
		}*/
		
		/*for(TestResult result : testResults) {
			dataWriter.saveTestResult(result);
		}*/
		
	}

}
