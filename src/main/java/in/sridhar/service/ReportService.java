package in.sridhar.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.sridhar.binding.CitizenPlan;
import in.sridhar.binding.SearchRequest;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<CitizenPlan> getCitizenPlans(SearchRequest request);

	public void exportExcel(HttpServletResponse response);

	public void exportPdf(HttpServletResponse response);

}
