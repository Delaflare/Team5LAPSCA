package sg.edu.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class PublicHolidays {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String holidayName;
	@NotEmpty
	private Date startDate;
	@NotEmpty
	private Date endDate;
}
