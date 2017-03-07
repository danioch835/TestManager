package classes;

import java.util.ArrayList;

public class Application {

	public static void main(String[] args) {
		ArrayList<TestResult> testResults = new ArrayList<TestResult>();
		DataReader dataReader = new ExcelDataReader("src/test/resources/PROFILE.xls");
		DataWriter dataWriter = new PDFDataWriter();
		
		for(int i = 0; i < dataReader.getNumberOfResults(); i++) {
			TestResult testResult = new TestResult(dataReader, dataWriter, i);
			testResult.loadTestResult();
			testResults.add(testResult);
			testResult.saveTestResult();
		}
		
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
