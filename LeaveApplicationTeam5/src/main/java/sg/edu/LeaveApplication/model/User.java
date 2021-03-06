package sg.edu.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message = "First name is required")
	private String firstName;
	@NotEmpty(message = "Last name is required")
	private String lastName;
	@NotEmpty(message = "User name is required")
	private String userName;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Email is required")
	@Email
	private String email;
	private Integer reportsTo;

	private String role;
	private Gender gender;
	private Title title;
	private Boolean enabled;
	
	

	@ManyToOne
    private Department department;
	@ManyToMany(mappedBy="user")
	private Collection<LeaveTypes> leaveTypes;
	@OneToMany(mappedBy="user")
	private Collection<LeaveRecord> leaveRecord;
	@OneToMany(mappedBy="user")
	private Collection<UserLeaveTypes> userLeaveTypes;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(@NotEmpty(message = "First name is required") String firstName,
			@NotEmpty(message = "Last name is required") String lastName,
			@NotEmpty(message = "User name is required") String userName,
			@NotEmpty(message = "Password is required") String password,
			@NotEmpty(message = "Email is required") @Email String email, Integer reportsTo, String role, Gender gender,
			Title title, Department department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.reportsTo = reportsTo;
		this.role = role;
		this.gender = gender;
		this.title = title;
		this.department = department;
	}

	public User(@NotEmpty(message = "First name is required") String firstName,
			@NotEmpty(message = "Last name is required") String lastName,
			@NotEmpty(message = "User name is required") String userName,
			@NotEmpty(message = "Password is required") String password,
			@NotEmpty(message = "Email is required") @Email String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Collection<LeaveTypes> getLeaveTypes() {
		return leaveTypes;
	}

	public void setLeaveTypes(Collection<LeaveTypes> leaveTypes) {
		this.leaveTypes = leaveTypes;
	}

	public Collection<LeaveRecord> getLeaveRecord() {
		return leaveRecord;
	}

	public void setLeaveRecord(Collection<LeaveRecord> leaveRecord) {
		this.leaveRecord = leaveRecord;
	}
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<UserLeaveTypes> getUserLeaveTypes() {
		return userLeaveTypes;
	}

	public void setUserLeaveTypes(Collection<UserLeaveTypes> userLeaveTypes) {
		this.userLeaveTypes = userLeaveTypes;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", reportsTo=" + reportsTo + ", role=" + role
				+ ", gender=" + gender + ", title=" + title + "]";
	}

	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
