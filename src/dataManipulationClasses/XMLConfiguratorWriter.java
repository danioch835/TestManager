package dataManipulationClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;


import testResultClasses.CategoryResult;

public class XMLConfiguratorWriter {

	private XMLInputFactory inputFactory;
	private XMLEventReader eventReader;
	private String xmlFilePath;
	private ArrayList<CategoryResult> tests;
	
	public XMLConfiguratorWriter(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
		
	}

	public ArrayList<CategoryResult> getResultCategories() {
		return tests;
	}
	
	public void loadTestCategories() {
		try {
			inputFactory = XMLInputFactory.newInstance();
			eventReader = inputFactory.createXMLEventReader(new InputStreamReader(new FileInputStream(xmlFilePath), Charset.forName("UTF-8")));
			tests = new ArrayList<CategoryResult>();
			
			boolean isTestProcessing = false;
			String actualElementName = "";
			String actualElementContent = "";
			CategoryResult actualTest = null;
			
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				int eventType = event.getEventType();
				if(eventType == XMLEvent.START_ELEMENT) {
					StartElement element = event.asStartElement();
					String elementName = element.getName().getLocalPart();
					
					if(isTestProcessing) {
						actualElementName = elementName;
					} else {
						if(elementName.equals("test")) {
							isTestProcessing = true;
						}
					}
				} else if(eventType == XMLEvent.END_ELEMENT) {
					EndElement element = event.asEndElement();
					String elementName = element.getName().getLocalPart();
					
					if(elementName.equals("test")) {
						tests.add(actualTest);
						isTestProcessing = false;
						actualElementContent = "";
						actualElementName = "";
						actualTest = null;
					} else if(elementName.equals("name")) {
						if(isTestProcessing) {
							actualTest = new CategoryResult(actualElementContent);
						}
					} else if(elementName.equals("category")) {
						if(isTestProcessing) {
							actualTest.addCategoryName(actualElementContent);
						}
					}
				} else if(eventType == XMLEvent.CHARACTERS) {
					if(isTestProcessing) {
						Characters elementContent = event.asCharacters();
						actualElementContent = elementContent.getData();
					}
					
				}
				
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
}
