package in.sridhar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sridhar.binding.CitizenPlan;
import in.sridhar.binding.SearchRequest;
import in.sridhar.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/planNames")
	public List<String> getPlanNames() {
		return reportService.getPlanNames();

	}

	@GetMapping("/planStatus")
	public List<String> getPlanStatus() {
		return reportService.getPlanStatus();

	}

	@GetMapping("/citizenPlans")
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {
		return reportService.getCitizenPlans(request);
	
	}
}
