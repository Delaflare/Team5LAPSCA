package sg.edu.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class LeaveTypes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String leaveName;
	@ManyToMany
	private Collection<User> user;
	
	public LeaveTypes() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public LeaveTypes(String leaveName) {
		super();
		this.leaveName = leaveName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LeaveTypes [id=" + id + ", leaveName=" + leaveName + "]";
	}

	
	}

