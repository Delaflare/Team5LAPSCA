package sg.edu.LeaveApplication.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class OTRecord {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@NotEmpty
	private int id;
	@ManyToOne
    private User user;
	@NotEmpty
	private LocalDate startDate;
	@NotEmpty
	private Integer duration;
	@NotEmpty
	private String status;
	@NotEmpty
	private int totalOTTime;
	@NotEmpty
	private Date submitDate;
	@NotEmpty
	private Date approvedDate;
	
	public OTRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public OTRecord(User user, @NotEmpty LocalDate startDate, @NotEmpty Integer duration, @NotEmpty String status,
			@NotEmpty int totalOTTime, @NotEmpty Date submitDate, @NotEmpty Date approvedDate) {
		super();
		this.user = user;
		this.startDate = startDate;
		this.duration = duration;
		this.status = status;
		this.totalOTTime = totalOTTime;
		this.submitDate = submitDate;
		this.approvedDate = approvedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public LocalDate getStartDate() {
		return startDate;
	}



	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	public Date getSubmitDate() {
		return submitDate;
	}



	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}



	public Date getApprovedDate() {
		return approvedDate;
	}



	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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

	public int getTotalOTTime() {
		return totalOTTime;
	}

	public void setTotalOTTime(int totalOTTime) {
		this.totalOTTime = totalOTTime;
	}

	@Override
	public String toString() {
		return "OTRecord [id=" + id + ", startDate=" + startDate + ", duration=" + duration + ", status=" + status
				+ ", totalOTTime=" + totalOTTime + "]";
	}
	 
	
}
