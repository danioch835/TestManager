package testResultClasses;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ExerciseCategory {
	private String name;
	private LinkedHashMap<String, PlayerResult> results;
	
	public ExerciseCategory() {};
	
	public ExerciseCategory(String name) {
		this.name = name;
		results = new LinkedHashMap<String, PlayerResult>();
	}
	
	public void addResult(String exercise, PlayerResult result) {
		results.put(exercise, result);
	}
	
	public int getNumberOfExercises() {
		return results.size();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public HashMap<String, PlayerResult> getResults() {
		return results;
	}
	
}
