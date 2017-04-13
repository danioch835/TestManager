package test;

import static org.junit.Assert.*;

import org.junit.Test;

import testResultClasses.PlayerResult;

public class PlayerResultTest {

	@Test
	public void test() {
		PlayerResult result = new PlayerResult();
		result.setActualResult(1.5);
		result.setLastResult(2.5);
		result.setTeamAverageResult(0.8);
		
		assertEquals(1.5, result.getActualResult(), 0.01);
		assertEquals(2.5, result.getLastResult(), 0.01);
		assertEquals(0.8, result.getTeamAverageResult(), 0.01);
		
	}

}
