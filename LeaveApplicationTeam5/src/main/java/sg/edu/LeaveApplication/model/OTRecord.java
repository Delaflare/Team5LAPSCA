package sg.edu.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class OTRecord {
	private Integer id;
	private Integer userId;
	private Date startDate;
	private Integer duration;
	private String status;
	private Integer totalOTTime;
	
	public OTRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OTRecord(Integer userId, Date startDate, Integer duration, String status, Integer totalOTTime) {
		super();
		this.userId = userId;
		this.startDate = startDate;
		this.duration = duration;
		this.status = status;
		this.totalOTTime = totalOTTime;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalOTTime() {
		return totalOTTime;
	}
	public void setTotalOTTime(Integer totalOTTime) {
		this.totalOTTime = totalOTTime;
	}
	
}
