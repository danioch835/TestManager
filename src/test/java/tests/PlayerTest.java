package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.DataReader;
import classes.ExcelDataReader;
import classes.Player;

public class PlayerTest {

	@Test
	public void getNameTest() {
		DataReader dataReader = new ExcelDataReader("test.xls");
		Player player = new Player(dataReader);
		player.loadName();
		assertEquals("Daniel", player.getName());
	}

}
