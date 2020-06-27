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
	private Integer id;
	private Integer userId;  
	private Integer LeaveTypeId;
	private Date startDate;
	private Integer duration;
	private Status status;
	private String description;
	private String workDissemination;
	private String contactDetails;
	private String comments;
	private Integer deptId;
	private Date leaveAppliedDate;
	private Date leaveApprovedDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLeaveTypeId() {
		return LeaveTypeId;
	}
	public void setLeaveTypeId(Integer leaveTypeId) {
		LeaveTypeId = leaveTypeId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorkDissemination() {
		return workDissemination;
	}
	public void setWorkDissemination(String workDissemination) {
		this.workDissemination = workDissemination;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public Date getLeaveAppliedDate() {
		return leaveAppliedDate;
	}
	public void setLeaveAppliedDate(Date leaveAppliedDate) {
		this.leaveAppliedDate = leaveAppliedDate;
	}
	public Date getLeaveApprovedDate() {
		return leaveApprovedDate;
	}
	public void setLeaveApprovedDate(Date leaveApprovedDate) {
		this.leaveApprovedDate = leaveApprovedDate;
	}
	
}
