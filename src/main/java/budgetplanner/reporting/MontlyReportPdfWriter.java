package budgetplanner.reporting;

import be.pxl.paj.budgetplanner.entity.Payment;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class MontlyReportPdfWriter {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DecimalFormat DECIMAL_FORMAT = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
	private static final Font CAT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static final Font SUB_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);


	public static ByteArrayInputStream generateDocument(MonthlyReportInformation monthlyExpensesReportInformation) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			addMetaData(document);
			addContent(document, monthlyExpensesReportInformation);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	private static void addMetaData(Document document) {
		document.addTitle("Inkomsten en uitgaven");
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Your name");
	}

	private static void addContent(Document document, MonthlyReportInformation reportInformation) throws DocumentException {
		Anchor anchor = new Anchor(reportInformation.getFullName() + " " + reportInformation.getFullDate(), CAT_FONT);
		anchor.setName("Maandoverzicht");
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph paraIncoming = new Paragraph("Inkomsten", SUB_FONT);
		Section subCatPart = catPart.addSection(paraIncoming);
		subCatPart.add(createTable(reportInformation.getIncoming()));
		addEmptyLine(paraIncoming, 2);

		Paragraph paraOutgoing = new Paragraph("Uitgaven", SUB_FONT);
		Section subCatPart2 = catPart.addSection(paraOutgoing);
		subCatPart2.add(createTable(reportInformation.getOutgoing()));
		addEmptyLine(paraOutgoing, 2);

		Paragraph paraResults = new Paragraph("Resultaat", SUB_FONT);
		Section result = catPart.addSection(paraResults);
		result.add(new Paragraph("Totale inkomen: " + reportInformation.getTotalIncoming()));
		result.add(new Paragraph("Totale uitgaven: " + reportInformation.getTotalOutgoing()));
		result.add(new Paragraph("Verschil: " + reportInformation.getDifference()));

		document.add(catPart);
	}

	private static PdfPTable createTable(List<Payment> transactions) {
		PdfPTable table = new PdfPTable(4);

		PdfPCell c1 = new PdfPCell(new Phrase("Datum"));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("Category"));
		c2.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c2);

		PdfPCell c3 = new PdfPCell(new Phrase("Bedrag"));
		c3.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c3);

		PdfPCell c4 = new PdfPCell(new Phrase("Naam"));
		c4.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(c4);

		table.setHeaderRows(1);

		for (Payment transaction : transactions) {
			table.addCell(DATE_FORMAT.format(transaction.getDate()));
			table.addCell(transaction.getCategory().getName());
			table.addCell(DECIMAL_FORMAT.format(transaction.getAmount()));
			table.addCell(transaction.getName());
		}

		return table;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
