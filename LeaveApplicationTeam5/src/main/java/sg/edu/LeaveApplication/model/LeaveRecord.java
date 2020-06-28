package sg.edu.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
@Entity
public class LeaveRecord {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private Date startDate;
	private int duration;
	private Status status;
	private String description;
	private String workDissemination;
	private String contactDetails;
	private String comments;
	@NotEmpty
	private Date leaveAppliedDate;
	private Date leaveApprovedDate;
	@ManyToOne
    private User user;
	@OneToOne
    private LeaveTypes LeaveTypes;
	
	
}
