package integrationTest;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataManipulationClasses.XMLConfigurator;

import org.junit.Before;
import org.junit.After;
import javax.xml.stream.*;

public class XMLConfiguratorTest {

	private String xmlFilePath = "testResources/TestFile.xml";
	
	@Before
	public void loadXMLFile() {
	}
	
	@Test
	public void getDatesPositionTest() {
		XMLConfigurator xmlConfigurator = new XMLConfigurator(xmlFilePath);
		int expectedRow = 1;
		int expectedColumn = 5;
		int expectedColumnLength = 2;
		int expectedRowLength = 1;
		xmlConfigurator.loadPositionsFromXML();
		assertEquals(expectedRow, xmlConfigurator.getDatesPosition().getRow());
		assertEquals(expectedColumn, xmlConfigurator.getDatesPosition().getColumn());
		assertEquals(expectedColumnLength, xmlConfigurator.getDatesPosition().getColumnLength());
		assertEquals(expectedRowLength, xmlConfigurator.getDatesPosition().getRowLength());
	}

	@Test
	public void getResultsPositionTest() {
		XMLConfigurator xmlConfigurator = new XMLConfigurator(xmlFilePath);
		int expectedRow = 3;
		int expectedColumn = 5;
		int expectedColumnLength = 1;
		int expectedRowLength = 1;
		xmlConfigurator.loadPositionsFromXML();
		assertEquals(expectedRow, xmlConfigurator.getResultsPosition().getRow());
		assertEquals(expectedColumn, xmlConfigurator.getResultsPosition().getColumn());
		assertEquals(expectedColumnLength, xmlConfigurator.getResultsPosition().getColumnLength());
		assertEquals(expectedRowLength, xmlConfigurator.getResultsPosition().getRowLength());
	}
	
	@Test
	public void getPlayersPositionTest() {
		XMLConfigurator xmlConfigurator = new XMLConfigurator(xmlFilePath);
		int expectedRow = 3;
		int expectedColumn = 2;
		int expectedColumnLength = 3;
		int expectedRowLength = 1;
		xmlConfigurator.loadPositionsFromXML();
		assertEquals(expectedRow, xmlConfigurator.getPlayersPosition().getRow());
		assertEquals(expectedColumn, xmlConfigurator.getPlayersPosition().getColumn());
		assertEquals(expectedColumnLength, xmlConfigurator.getPlayersPosition().getColumnLength());
		assertEquals(expectedRowLength, xmlConfigurator.getPlayersPosition().getRowLength());
	}
	
	@Test
	public void getExercisesNamesPositionTest() {
		XMLConfigurator xmlConfigurator = new XMLConfigurator(xmlFilePath);
		int expectedRow = 2;
		int expectedColumn = 5;
		int expectedColumnLength = 1;
		int expectedRowLength = 1;
		xmlConfigurator.loadPositionsFromXML();
		assertEquals(expectedRow, xmlConfigurator.getExercisesNamesPosition().getRow());
		assertEquals(expectedColumn, xmlConfigurator.getExercisesNamesPosition().getColumn());
		assertEquals(expectedColumnLength, xmlConfigurator.getExercisesNamesPosition().getColumnLength());
		assertEquals(expectedRowLength, xmlConfigurator.getExercisesNamesPosition().getRowLength());
	}

}
