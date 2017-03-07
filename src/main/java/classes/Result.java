package classes;

public class Result {
	private String name;
	private Double playerResult;
	private Double teamAverageResult;
	
	public Result(String name) {
		this.name = name;
	}
	
	public void setPlayerResult(Double result) {
		playerResult = result;
	}
	
	public void setTeamAverageResult(Double result) {
		teamAverageResult = result;
	}
	
	public String getResultName() {
		return name;
	}
	
	public Double getPlayerResult() {
		return playerResult;
	}
	
	public Double getTeamAverageResult() {
		return teamAverageResult;
	}
}
