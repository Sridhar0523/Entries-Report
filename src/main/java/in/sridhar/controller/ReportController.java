package in.sridhar.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sridhar.binding.CitizenPlan;
import in.sridhar.binding.SearchRequest;
import in.sridhar.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> planNames = reportService.getPlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getplanStatus() {
		List<String> planNames = reportService.getPlanStatus();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> search(@RequestBody SearchRequest request) {
		List<CitizenPlan> citizenPlans = reportService.getCitizenPlans(request);
		return new ResponseEntity<>(citizenPlans, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String key = "Content-Disposition";
		String value = "attachment;file=citizens.xlsx";

		response.setHeader(key, value);
		reportService.exportExcel(response);
		response.flushBuffer();

	}

	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");

		String key = "Content-Disposition";
		String value = "attachment;plans.pdf";

		response.setHeader(key, value);
		reportService.exportPdf(response);
		response.flushBuffer();

	}

}
