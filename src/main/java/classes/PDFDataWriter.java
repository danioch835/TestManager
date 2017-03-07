package classes;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFDataWriter implements DataWriter {

	private Document document;
	private Font font;
	
	public PDFDataWriter() {
		try {
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			font = new Font(baseFont, 12);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveTestResult(Player player, Test test) {
		String fileName = player.getLastname() + player.getName();
		openFile(fileName);
		savePlayer(player);
		saveTest(test);
		closeFile();
	}
	
	private void openFile(String fileName) {
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/" + fileName + ".pdf"));
			document.open();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void closeFile() {
		document.close();
	}
	
	private void saveTest(Test test) {
		ArrayList<TestCategory> testCategories = test.getCategories();
		
		for(TestCategory testCategory : testCategories) {
			
			PdfPTable table = new PdfPTable(4);
			table.setSpacingBefore(5);
			table.setSpacingAfter(5);
			saveTestCateogry(testCategory.getCategoryName(), table);	
			ArrayList<Exercise> exercises = testCategory.getExercises();
			for(Exercise exercise : exercises) {	
				saveExercise(exercise, table);
				ArrayList<Result> results = exercise.getResults();	
				for(Result result : results) {
					saveResult(result, table);
				}	
			}
			try {
				document.add(table);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void saveTestCateogry(String categoryToAddName, PdfPTable table) {
		String categoryName = categoryToAddName.substring(categoryToAddName.indexOf("TEST")+"TEST".length());
		PdfPCell cell = new PdfPCell(new Phrase(categoryName, font));
		cell.setColspan(2);
		addTestCategoryCell(table, cell);
		cell = new PdfPCell(new Phrase("Wynik zawodnika", font));
		addTestCategoryCell(table, cell);
		cell = new PdfPCell(new Phrase("Œrednia dru¿yny", font));
		addTestCategoryCell(table, cell);
	}
	
	private void addTestCategoryCell(PdfPTable table, PdfPCell cell) {
		BaseColor cellColor = new BaseColor(36, 174, 0);
		cell.setBackgroundColor(cellColor);
		addHorizontalAligmentCell(table, cell);
	}
	
	private void saveExercise(Exercise exerciseToAdd, PdfPTable table) {
		int numberOfResults = exerciseToAdd.getResultsNames().size();
		PdfPCell cell = new PdfPCell(new Phrase(exerciseToAdd.getExerciseName(), font));
		cell.setBackgroundColor(new BaseColor(38, 184, 0));
		cell.setRowspan(numberOfResults);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		addHorizontalAligmentCell(table, cell);
	}
	
	private void saveResult(Result resultToAdd, PdfPTable table) {
		PdfPCell cell = new PdfPCell(new Phrase(resultToAdd.getResultName(), font));
		cell.setBackgroundColor(new BaseColor(97, 238, 60));
		addHorizontalAligmentCell(table, cell);
		cell = new PdfPCell(new Phrase(resultToAdd.getPlayerResult().toString(), font));
		cell.setBackgroundColor(new BaseColor(112, 235, 79));
		addHorizontalAligmentCell(table, cell);
		cell = new PdfPCell(new Phrase(resultToAdd.getTeamAverageResult().toString(), font));
		cell.setBackgroundColor(new BaseColor(112, 235, 79));
		addHorizontalAligmentCell(table, cell);
	}
	
	private void addHorizontalAligmentCell(PdfPTable table, PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}
	
	private void savePlayer(Player player) {
		try {
			document.add(new Paragraph("Dane zawodnika", font));
			document.add(new Paragraph("Imie: " + player.getName(), font));
			document.add(new Paragraph("Nazwisko: " + player.getLastname(), font));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
