package sg.edu.LeaveApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
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
	private String reportsTo;
	private String deptId; 

	private Role role;
	private Gender gender;
	private Title title;
	
	@ManyToOne
    private Department department;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, @NotEmpty(message = "First name is required") String firstName,
			@NotEmpty(message = "Last name is required") String lastName,
			@NotEmpty(message = "User name is required") String userName,
			@NotEmpty(message = "Password is required") String password,
			@NotEmpty(message = "Email is required") @Email String email, Role role, String reportsTo, Gender gender,
			String deptId, Title title) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.reportsTo = reportsTo;
		this.gender = gender;
		this.deptId = deptId;
		this.title = title;
	}

	public User(@NotEmpty(message = "First name is required") String firstName,
			@NotEmpty(message = "Last name is required") String lastName,
			@NotEmpty(message = "User name is required") String userName,
			@NotEmpty(message = "Password is required") String password,
			@NotEmpty(message = "Email is required") @Email String email, Role role, String reportsTo, Gender gender,
			String deptId, Title title) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.reportsTo = reportsTo;
		this.gender = gender;
		this.deptId = deptId;
		this.title = title;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", email=" + email + ", reportsTo=" + reportsTo + ", deptId=" + deptId
				+ "]";
	}
	
	
}
