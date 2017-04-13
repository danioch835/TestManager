package dataManipulationClasses;

import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class BackgroundPage extends PdfPageEventHelper{
	private PdfImportedPage backgroundPage;
	
	public void addBackground(PdfWriter writer) {
		writer.setPageEvent(this);
		try {
			PdfReader reader = new PdfReader("testResources/siemacha.pdf");
			backgroundPage = writer.getImportedPage(reader, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onEndPage(PdfWriter writer, Document document) {
		writer.getDirectContentUnder().addTemplate(backgroundPage, 0, 0);
	}
}
