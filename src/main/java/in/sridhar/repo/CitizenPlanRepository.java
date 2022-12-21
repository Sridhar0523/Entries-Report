package in.sridhar.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sridhar.binding.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Serializable> {

}
