package integrationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataManipulationClasses.XMLConfiguratorWriter;
import testResultClasses.CategoryResult;

public class XMLConfiguratorWriterTest {

	private String xmlFilePath = "testResources/testWriter.xml";
	
	@Test
	public void getTestsCategoriesTest() {
		XMLConfiguratorWriter writerConfigurator = new XMLConfiguratorWriter(xmlFilePath);
		writerConfigurator.loadTestCategories();
		ArrayList<CategoryResult> resultCategories = writerConfigurator.getResultCategories();
		
		assertEquals(2, resultCategories.size());
		
	}

}
