package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import testResultClasses.Player;

public class PlayerTest {

	@Test
	public void test() {
		Player player = new Player();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date date = null;
		try {
			date = sdf.parse("09.01.1993");
		} catch (ParseException e) {
			assertFalse("Can not parse data", true);
		}
		player.setName("Name");
		player.setLastname("Lastname");
		player.setDateOfBirth(date);
		
		assertEquals("Name", player.getName());
		assertEquals("Lastname", player.getLastname());
		assertEquals(date, player.getDateOfBirth());
		
	}

}
