package classes;

import java.util.ArrayList;

public class TestCategory {
	private String name;
	private ArrayList<Exercise> exercises;
	
	public TestCategory(String name) {
		this.name = name;
		exercises = new ArrayList<Exercise>();
	}
	
	public ArrayList<Exercise> getExercises() {
		return exercises;
	}
	
	public ArrayList<String> getExercisesNames() {
		ArrayList<String> exercisesNames = new ArrayList<String>();
		
		for(Exercise exercise : exercises) {
			exercisesNames.add(exercise.getExerciseName());
		}
		return exercisesNames;
	}
	
	public String getCategoryName() {
		return name;
	}
	
	public void addExercise(Exercise exercise) {
		exercises.add(exercise);
	}
}
