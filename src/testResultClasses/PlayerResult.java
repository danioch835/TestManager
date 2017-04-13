package testResultClasses;

public class PlayerResult {
	private double actualResult;
	private double lastResult;
	private double teamAverageResult;
	
	public PlayerResult() {
	}
	
	public void setActualResult(double actualResult) {
		this.actualResult = actualResult;
	}
	public double getActualResult() {
		return actualResult;
	}
	public void setLastResult(double lastResult) {
		this.lastResult = lastResult;
	}
	public double getLastResult() {
		return lastResult;
	}
	public void setTeamAverageResult(double teamAverageResult) {
		this.teamAverageResult = teamAverageResult;
	}
	public double getTeamAverageResult() {
		return teamAverageResult;
	}
}
