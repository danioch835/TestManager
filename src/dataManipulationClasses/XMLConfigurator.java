package dataManipulationClasses;

import java.awt.Event;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import javafx.event.EventType;

public class XMLConfigurator {
	private XMLInputFactory xmlInputFactory;
	private XMLEventReader xmlEventReader;
	private DataPosition testDatesPosition;
	private DataPosition resultsPosition;
	private DataPosition playersPosition;
	private DataPosition exercisesNamesPosition;
	private String xmlFilePath;
	
	public XMLConfigurator(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	
	public DataPosition getDatesPosition() {
		return testDatesPosition;
	}

	public DataPosition getResultsPosition() {
		return resultsPosition;
	}
	
	public DataPosition getPlayersPosition() {
		return playersPosition;
	}
	
	public DataPosition getExercisesNamesPosition() {
		return exercisesNamesPosition;
	}
	
	public void loadPositionsFromXML() {
		try {
			xmlInputFactory = xmlInputFactory.newInstance();
			xmlEventReader = xmlInputFactory.createXMLEventReader(new FileReader(xmlFilePath));
			
			boolean isPositionProcessing = false;
			DataPosition processingPosition = null;
			String actualAtrribute = "";
			String actualElementName = "";
			String actualElementContent = "";
			
			while(xmlEventReader.hasNext()) {
				XMLEvent event = xmlEventReader.nextEvent();
				int eventType = event.getEventType();
				
				if(eventType == XMLEvent.START_ELEMENT) {
					StartElement element = event.asStartElement();
					String elementName = element.getName().getLocalPart();
					
					if(isPositionProcessing) {
						actualElementName = elementName;
					} else {
						if(elementName.equalsIgnoreCase("index")) {
							isPositionProcessing = true;
						}
					}
					
					
				} else if(eventType == XMLEvent.END_ELEMENT) {
					if(isPositionProcessing) {
						EndElement element = event.asEndElement();
						String elementName = element.getName().getLocalPart();
						if(elementName.equals("index")) {
							isPositionProcessing = false;
							processingPosition = null;
							actualAtrribute = "";
							actualElementContent = "";
							actualElementName = "";
						} else if(elementName.equals("name")) {
							if(actualElementContent.equals("test dates")) {
								testDatesPosition = new DataPosition();
								processingPosition = testDatesPosition;
							} else if(actualElementContent.equals("results")) {
								resultsPosition = new DataPosition();
								processingPosition = resultsPosition;
							} else if(actualElementContent.equals("players")) {
								playersPosition = new DataPosition();
								processingPosition = playersPosition;
							} else if(actualElementContent.equals("exercises names")) {
								exercisesNamesPosition = new DataPosition();
								processingPosition = exercisesNamesPosition;
							}
						} else if(elementName.equals("row")) {
							int row = Integer.parseInt(actualElementContent);
							if(processingPosition != null) {
								processingPosition.setRow(row);
							}
						} else if(elementName.equals("column")) {
							int column = Integer.parseInt(actualElementContent);
							if(processingPosition != null) {
								processingPosition.setColumn(column);
							}
						} else if(elementName.equals("columnLength")) {
							int columnLength = Integer.parseInt(actualElementContent);
							if(processingPosition != null) {
								processingPosition.setColumnLength(columnLength);
							}
						} else if(elementName.equals("rowLength")) {
							int rowLength = Integer.parseInt(actualElementContent);
							if(processingPosition != null) {
								processingPosition.setRowLenth(rowLength);
							}
						}
					}
					
				} else if(eventType == XMLEvent.CHARACTERS) {
					if(isPositionProcessing) {
						Characters characters = event.asCharacters();
						actualElementContent = characters.getData();
					}
				}
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(XMLStreamException e) {
			e.printStackTrace();
		}
	}
	
	private void loadDatesPosition() {
		
	}
	private void loadResultsPosition() {
		
	}
	
	private void loadPlayersPosition() {
		
	}
	
	private void loadExercisesNamesPosition() {
		
	}
	
}
