package in.sridhar.binding;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZENS-PLANS-INFO")
public class CitizenPlan {

	private Integer cid;
	private String planName;
	private String planStatus;
	private String cname;
	private String cemail;
	private String gender;
	private Long phno;
	private Long ssn;

}
