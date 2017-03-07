package classes;

import java.util.ArrayList;

public class Exercise {
	private String name;
	private ArrayList<Result> results;
	
	public Exercise(String name) {
		results = new ArrayList<Result>();
		this.name = name;
	}
	
	public String getExerciseName() {
		return name;
	}
	
	public void addResult(Result newResult) {
		results.add(newResult);
	}
	
	public ArrayList<String> getResultsNames() {
		ArrayList<String> resultNames = new ArrayList<String>();
		for(Result result : results) {
			resultNames.add(result.getResultName());
		}
		return resultNames;
	}
	
	public ArrayList<Result> getResults() {
		return results;
	}
}
