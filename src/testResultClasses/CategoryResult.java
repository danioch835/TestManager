package testResultClasses;

import java.util.ArrayList;

public class CategoryResult {

	private String name;
	private ArrayList<ExerciseCategory> categories;
	private ArrayList<String> categoriesNames;
	
	public CategoryResult(String name) {
		categories = new ArrayList<ExerciseCategory>();
		categoriesNames = new ArrayList<String>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addCategoryName(String categoryName) {
		categoriesNames.add(categoryName);
	}
	
	public void addCategory(ExerciseCategory category) {
		categories.add(category);
	}
	
	public boolean isTestCategory(String name) {
		return categoriesNames.contains(name);
	}
	
	public ArrayList<ExerciseCategory> getCategories() {
		return categories;
	}
	
}
