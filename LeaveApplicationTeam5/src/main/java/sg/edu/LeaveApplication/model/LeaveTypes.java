package sg.edu.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class LeaveTypes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String leaveName;
	private Integer leaveDays;
	@ManyToMany
	private Collection<User> user;
	@ManyToOne
	private LeaveRecord leaveRecord;
	
	
	public LeaveTypes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveTypes(String leaveName, Integer leaveDays, Collection<User> user, LeaveRecord leaveRecord) {
		super();
		this.leaveName = leaveName;
		this.leaveDays = leaveDays;
		this.user = user;
		this.leaveRecord = leaveRecord;
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

	public Integer getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(Integer leaveDays) {
		this.leaveDays = leaveDays;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}

	public LeaveRecord getLeaveRecord() {
		return leaveRecord;
	}

	public void setLeaveRecord(LeaveRecord leaveRecord) {
		this.leaveRecord = leaveRecord;
	}

	@Override
	public String toString() {
		return "LeaveTypes [id=" + id + ", leaveName=" + leaveName + ", leaveDays=" + leaveDays + "]";
	}

	
	}

