package sg.edu.LeaveApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserLeaveTypes {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private int userId;
	@NotEmpty
	private int leaveTypeId;
	@NotEmpty
	private int leaveAllowance;
	
}
