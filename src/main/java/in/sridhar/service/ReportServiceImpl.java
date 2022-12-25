package in.sridhar.service;

import java.awt.Color;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.sridhar.binding.CitizenPlan;
import in.sridhar.binding.SearchRequest;
import in.sridhar.repo.CitizenPlanRepository;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = citizenPlanRepository.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> planStatus = citizenPlanRepository.getPlanStatus();
		return planStatus;
	}

	@Override
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}

		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (request.getGender() != null && !request.getGender().equals("")) {
			entity.setGender(request.getGender());
		}

		Example<CitizenPlan> example = Example.of(entity);

		List<CitizenPlan> records = citizenPlanRepository.findAll(example);

		return records;

	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("citizens Info");
		XSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("id");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("SSN");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Plan Name");
		headerRow.createCell(5).setCellValue("Plan Status");

		List<CitizenPlan> records = citizenPlanRepository.findAll();

		int dataRowIndex = 1;
		for (CitizenPlan record : records) {
			XSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(record.getCid());
			dataRow.createCell(1).setCellValue(record.getCname());
			dataRow.createCell(2).setCellValue(record.getSsn());
			dataRow.createCell(3).setCellValue(record.getGender());
			dataRow.createCell(4).setCellValue(record.getPlanName());
			dataRow.createCell(5).setCellValue(record.getPlanStatus());

			dataRowIndex++;

			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("Citizens Plans Info", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		// set Table header data

		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setBackgroundColor(Color.BLUE);
		pdfPCell.setPadding(5);

		Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
		font2.setColor(Color.WHITE);

		pdfPCell.setPhrase(new Phrase("ID", font2));
		table.addCell(pdfPCell);

		pdfPCell.setPhrase(new Phrase("Name", font2));
		table.addCell(pdfPCell);

		pdfPCell.setPhrase(new Phrase("SSN", font2));
		table.addCell(pdfPCell);

		pdfPCell.setPhrase(new Phrase("Gender", font2));
		table.addCell(pdfPCell);

		pdfPCell.setPhrase(new Phrase("Plan Name", font2));
		table.addCell(pdfPCell);

		pdfPCell.setPhrase(new Phrase("Plan Status", font2));
		table.addCell(pdfPCell);

		// set Table data

		List<CitizenPlan> record = citizenPlanRepository.findAll();

		for (CitizenPlan record1 : record) {
			table.addCell(String.valueOf(record1.getCid()));
			table.addCell(record1.getCname());
			table.addCell(String.valueOf(record1.getSsn()));
			table.addCell(record1.getGender());
			table.addCell(record1.getPlanName());
			table.addCell(record1.getPlanStatus());
		}

		document.add(table);
		document.close();

	}

}
