package in.sridhar.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sridhar.binding.CitizenPlan;
import in.sridhar.binding.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportService reportService;

	@Override
	public List<String> getPlanNames() {
		List<String> planNames = reportService.getPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> planStatus = reportService.getPlanStatus();
		return planStatus;
	}

	@Override
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {
		List<CitizenPlan> citizenPlans = reportService.getCitizenPlans(request);
		return citizenPlans;
	}

	@Override
	public void exportExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportPdf(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
