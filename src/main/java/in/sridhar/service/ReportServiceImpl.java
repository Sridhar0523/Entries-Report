package in.sridhar.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
	public void exportExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportPdf(HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
