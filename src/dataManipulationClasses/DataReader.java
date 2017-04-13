package dataManipulationClasses;

import java.util.ArrayList;
import java.util.Date;

import testResultClasses.ExerciseCategory;
import testResultClasses.Player;

public interface DataReader {
	public ArrayList<ExerciseCategory> loadCategoryResults(int categoryIndex);
	public int getNumberOfCategories();
	public ArrayList<Player> loadPlayers();
	public Date getActualTestDate();
	public Date getLastTestDate();
}
