package in.sridhar.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.sridhar.binding.CitizenPlan;
import in.sridhar.repo.CitizenPlanRepository;

@Component
public class DataInserter implements ApplicationRunner {
	
	@Autowired
	private CitizenPlanRepository citizenPlanRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		CitizenPlan citizenPlan = new CitizenPlan();
		citizenPlan.setCname("Ashok");
		citizenPlan.setGender("Male");
		citizenPlan.setCemail("ashok@gmail.com");
		citizenPlan.setPhno(900000000L);
		citizenPlan.setPlanName("SNAP");
		citizenPlan.setPlanStatus("Approved");
		citizenPlan.setSsn(1000000009L);
		
		CitizenPlan citizenPlan1 = new CitizenPlan();
		citizenPlan1.setCname("Rakesh");
		citizenPlan1.setGender("Male");
		citizenPlan1.setCemail("rakesh@gmail.com");
		citizenPlan1.setPhno(9000000002L);
		citizenPlan1.setPlanName("SNAP");
		citizenPlan1.setPlanStatus("Denied");
		citizenPlan1.setSsn(2000000009L);
		
		CitizenPlan citizenPlan2 = new CitizenPlan();
		citizenPlan2.setCname("Akhila");
		citizenPlan2.setGender("Fe-Male");
		citizenPlan2.setCemail("akila@gmail.com");
		citizenPlan2.setPhno(9000000003L);
		citizenPlan2.setPlanName("CCAP");
		citizenPlan2.setPlanStatus("Approved");
		citizenPlan2.setSsn(3000000009L);
		
		CitizenPlan citizenPlan3 = new CitizenPlan();
		citizenPlan3.setCname("Bhanu");
		citizenPlan3.setGender("Fe-Male");
		citizenPlan3.setCemail("bhanu@gmail.com");
		citizenPlan3.setPhno(9000000004L);
		citizenPlan3.setPlanName("CCAP");
		citizenPlan3.setPlanStatus("Denied");
		citizenPlan3.setSsn(4000000009L);
		
		List<CitizenPlan> list = Arrays.asList(citizenPlan,citizenPlan1,citizenPlan2,citizenPlan3);
		
		citizenPlanRepository.saveAll(list);
		
	}
	
	
	

}
