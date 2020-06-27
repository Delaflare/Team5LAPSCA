package sg.edu.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
@Entity
public class LeaveRecord {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private int userId;
	@NotEmpty
	private int LeaveTypeId;
	@NotEmpty
	private Date startDate;
	@NotEmpty
	private int duration;
	@NotEmpty
	private Status status;
	@NotEmpty
	private String description;
	@NotEmpty
	private String workDissemination;
	@NotEmpty
	private String contactDetails;
	@NotEmpty
	private String comments;
	@NotEmpty
	private int deptId;
	@NotEmpty
	private Date leaveAppliedDate;
	@NotEmpty
	private Date leaveApprovedDate;
}
