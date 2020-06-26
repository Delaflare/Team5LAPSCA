package sg.edu.LeaveApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import sg.edu.iss.End2End.model.Department;


@Entity
public class User {

	
	
	

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;
	@NotEmpty @Email
	private String email;
	@NotEmpty 
	private Role role;
	@NotEmpty 
	private String reportsTo;
	@NotEmpty 
	private Gender gender;
	@NotEmpty 
	private String deptId;
	@NotEmpty 
	private Title title;
	
	@ManyToOne
	private Department department;
	
	
}
