package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ExcelDataReader;
import classes.Player;

public class ExcelReaderTest {
	
	@Before
	public void initFile() {
	}
	
	@Test
	public void readPlayerTest() {
		ExcelDataReader reader = new ExcelDataReader("src/test/resources/test.xls");
		Player player = reader.getPlayer();
		assertEquals("Daniel", player.getName());
		assertEquals("Ochojski", player.getLastname());
	}

	@Test
	public void testLoadTest() {
		ExcelDataReader reader = new ExcelDataReader("src/test/resources/test.xls");
		classes.Test test = reader.getTest();
		assertEquals(2, test.getNumberOfCategories());
	}
	
}
