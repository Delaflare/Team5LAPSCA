package sg.edu.LeaveApplication.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

@Entity
public class OTRecord {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
    private User user;
	@PastOrPresent
	private LocalDate startDate;
	@PastOrPresent
	private LocalDate endDate;
	private String description;
	private Status status;
	private int totalOTTime;
	private Date submitDate;
	private Date approvedDate;
	
	public OTRecord() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OTRecord(@PastOrPresent LocalDate startDate, @PastOrPresent LocalDate endDate, Status status,
			int totalOTTime, Date submitDate, Date approvedDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
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

	
	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
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
		return "OTRecord [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
				+ ", totalOTTime=" + totalOTTime + ", submitDate=" + submitDate + ", approvedDate=" + approvedDate
				+ "]";
	}

	
}
