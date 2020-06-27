package sg.edu.LeaveApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class UserLeaveTypes {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	
	
	
	@NotEmpty
	private int userId;// delete when agreed
	@ManyToOne
    private User user;
	
	
	
	
	@NotEmpty
	private int leaveTypeId;// delete when agreed
	@ManyToOne
    private  LeaveTypes LeaveTypes;
	
	
	@NotEmpty
	private int leaveAllowance;
	
}
