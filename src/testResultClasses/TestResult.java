package testResultClasses;

import java.util.ArrayList;
import java.util.Date;

public class TestResult {
	
	private Player player;
	private ArrayList<ExerciseCategory> categories;
	private Date actualTestDate;
	private Date lastTestDate;
	
	
	public TestResult(Player player) {
		this.player = player;
		categories = new ArrayList<ExerciseCategory>();
	}

	public TestResult() {
		categories = new ArrayList<ExerciseCategory>();
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setActualTestDate(Date date) {
		this.actualTestDate = date;
	}
	
	public Date getActualTestDate() {
		return actualTestDate;
	}
	
	public void setLastTestDate(Date date) {
		this.lastTestDate = date;
	}
	
	public Date getLastTestDate() {
		return lastTestDate;
	}
	
	public void addCategory(ExerciseCategory category) {
		categories.add(category);
	}
	
	public void addResultToCategory(String categoryName, String exerciseName, PlayerResult result) {
		for(ExerciseCategory category : categories) {
			if(categoryName.equals(category.getName())) {
				category.addResult(exerciseName, result);
			}
		}
	}
	
	public ExerciseCategory getCategoryResults(String categoryName) {
		ExerciseCategory results = null;
		for(ExerciseCategory category : categories) {
			if(categoryName.equals(category.getName())) {
				results = category;
			}
		}
		return results;
	}
	
	public ArrayList<ExerciseCategory> getCategories() {
		return categories;
	}
	
	public int getNumberOfCategories() {
		return categories.size();
	}
}