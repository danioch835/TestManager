package dataManipulationClasses;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.paint.Color;
import testResultClasses.CategoryResult;
import testResultClasses.ExerciseCategory;
import testResultClasses.Player;
import testResultClasses.PlayerResult;
import testResultClasses.TestResult;

public class PDFDataWriter implements DataWriter {

	private XMLConfiguratorWriter writerConfigurator;
	private Document document;
	private Font font;
	
	public PDFDataWriter() {
		try {
			writerConfigurator = new XMLConfiguratorWriter("testResources/testWriter.xml");
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(baseFont, 12);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveTestResult(TestResult result) {
		writerConfigurator.loadTestCategories();
		ArrayList<CategoryResult> resultsInCategoires = writerConfigurator.getResultCategories();
		
		for(CategoryResult category : resultsInCategoires) {
			ArrayList<ExerciseCategory> categories = result.getCategories();
			for(ExerciseCategory categoryExercise : categories) {
				if(category.isTestCategory(categoryExercise.getName())) {
					category.addCategory(categoryExercise);
				}
			}
		}
		
		Player player = result.getPlayer();
		Date actualTestDate = result.getActualTestDate();
		Date lastTestDate = result.getLastTestDate();
		
		document = new Document();
		
		for(CategoryResult category : resultsInCategoires) {
			if(document.isOpen()) {
				document.newPage();
			} else {
				String fileName = player.getLastname() + player.getName();
				openFile(fileName);
				writePlayerInformation(player);
			}
			writeTestHeader(category.getName());
			writeTestResult(category, actualTestDate, lastTestDate);
		}
		closeFile();
	}
	
	private void writeTestHeader(String header) {
		try {
			float oldSize = font.getSize();
			int oldStyle = font.getStyle();
			font.setSize(18);
			font.setStyle(Font.BOLD);
			Paragraph headerParagraph = new Paragraph(header, font);
			headerParagraph.setSpacingBefore(50);
			headerParagraph.setSpacingAfter(5);
			headerParagraph.setAlignment(Element.ALIGN_CENTER);
			document.add(Chunk.NEWLINE);
			document.add(headerParagraph);
			font.setSize(oldSize);
			font.setStyle(oldStyle);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private void writePlayerInformation(Player player) {
		try {
			float oldSize = font.getSize();
			font.setSize(32);
			Paragraph playerParagraph = new Paragraph(player.getName() + " " + player.getLastname(), font);
			playerParagraph.setAlignment(Element.ALIGN_CENTER);
			document.add(playerParagraph);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			if(player.getDateOfBirth() != null) {
				String dateOfBirth = sdf.format(player.getDateOfBirth());
				font.setSize(18);
				Paragraph dateParagraph = new Paragraph(dateOfBirth, font);
				dateParagraph.setAlignment(Element.ALIGN_CENTER);
				dateParagraph.setSpacingBefore(15);
				document.add(dateParagraph);
			}
			font.setSize(oldSize);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void writeTestResult(CategoryResult result, Date actual, Date last) {
		ArrayList<ExerciseCategory> categories = result.getCategories();
		SimpleDateFormat sdf = new SimpleDateFormat("MM.yyyy");
		for(ExerciseCategory category : categories) {
			PdfPTable table = new PdfPTable(new float[] {45, 15, 15, 25});
			PdfPCell cell  = new PdfPCell();
			cell.setPadding(5);
			Phrase phrase = new Phrase();
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			cell.setBorder(Rectangle.BOX);
			
			phrase = new Phrase(sdf.format(last), font);
			cell.setPhrase(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			phrase = new Phrase(sdf.format(actual), font);
			cell.setPhrase(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			phrase = new Phrase("Œrednia dru¿yny", font);
			cell.setPhrase(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell.setBackgroundColor(BaseColor.WHITE);
			
			HashMap<String, PlayerResult> exercisesResults = category.getResults();
			for(Map.Entry<String, PlayerResult> results : exercisesResults.entrySet()) {
				PlayerResult playerResult = results.getValue();
				
				String exerciseName = results.getKey();
				if(exerciseName.equals("NN")) {
					exerciseName = "Noga niewiod¹ca";
				} else if(exerciseName.equals("NW")) {
					exerciseName = "Noga wiod¹ca";
				} else if(exerciseName.equals("2 okr[s]")) {
					exerciseName = "Bieg po kopercie [s]";
				}
				
				exerciseName = exerciseName.toLowerCase();
				exerciseName = String.join("", exerciseName.substring(0, 1).toUpperCase(), exerciseName.substring(1));
				
				phrase = new Phrase(exerciseName, font);
				cell.setPhrase(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				String lastResult = "";
				Double lastResultNumber = playerResult.getLastResult();
				if(lastResultNumber == Math.rint(lastResultNumber)) {
					lastResult = (lastResultNumber == -1) ? "-" : Integer.toString(lastResultNumber.intValue());
				} else {
					lastResult = (lastResultNumber == -1) ? "-" : Double.toString(lastResultNumber);
				}
				phrase = new Phrase(lastResult, font);
				cell.setPhrase(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				String actualResult = "";
				Double actualResultNumber = playerResult.getActualResult();
				if(actualResultNumber == Math.rint(actualResultNumber)) {
					actualResult = (actualResultNumber == -1) ? "-" : Integer.toString(actualResultNumber.intValue());
				} else {
					actualResult = (actualResultNumber == -1) ? "-" : Double.toString(actualResultNumber);
				}
				phrase = new Phrase(actualResult, font);
				cell.setPhrase(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				String teamAverage = "";
				Double teamAverageNumber = playerResult.getTeamAverageResult();
				if(teamAverageNumber == Math.rint(teamAverageNumber)) {
					teamAverage = (teamAverageNumber == -1) ? "-" : Integer.toString(teamAverageNumber.intValue());
				} else {
					teamAverage = (teamAverageNumber == -1) ? "-" : Double.toString(teamAverageNumber);
				}
				phrase = new Phrase(teamAverage, font);
				cell.setPhrase(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			try {
				float oldSize = font.getSize();
				int oldStyle = font.getStyle();
				font.setSize(14);
				font.setStyle(Font.BOLD);
				Paragraph categoryName = new Paragraph(category.getName(), font);
				categoryName.setSpacingBefore(15);
				categoryName.setSpacingAfter(10);
				categoryName.setAlignment(Element.ALIGN_CENTER);
				document.add(categoryName);
				font.setSize(oldSize);
				font.setStyle(oldStyle);
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void openFile(String fileName) {
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("testResources/resultPDF/" + fileName + ".pdf"));
			BackgroundPage bakgroundPage = new BackgroundPage();
			bakgroundPage.addBackground(writer);
			document.open();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void closeFile() {
		document.close();
	}

}
