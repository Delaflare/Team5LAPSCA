package sg.edu.LeaveApplication.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
@Entity
public class LeaveRecord {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private Date startDate;
	private int duration;
	private Status status;
	private String description;
	private String workDissemination;
	private String contactDetails;
	private String comments;
	@NotEmpty
	private Date leaveAppliedDate;
	private Date leaveApprovedDate;
	@ManyToOne
    private User user;
	@OneToMany(mappedBy="leaveRecord")
	private Collection<LeaveTypes> leaveTypes;
	
	public LeaveRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveRecord(@NotEmpty Date startDate, int duration, Status status, String description,
			String workDissemination, String contactDetails, String comments, @NotEmpty Date leaveAppliedDate,
			Date leaveApprovedDate, User user, Collection<LeaveTypes> leaveTypes) {
		super();
		this.startDate = startDate;
		this.duration = duration;
		this.status = status;
		this.description = description;
		this.workDissemination = workDissemination;
		this.contactDetails = contactDetails;
		this.comments = comments;
		this.leaveAppliedDate = leaveAppliedDate;
		this.leaveApprovedDate = leaveApprovedDate;
		this.user = user;
		this.leaveTypes = leaveTypes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<LeaveTypes> getLeaveTypes() {
		return leaveTypes;
	}

	public void setLeaveTypes(Collection<LeaveTypes> leaveTypes) {
		this.leaveTypes = leaveTypes;
	}

	@Override
	public String toString() {
		return "LeaveRecord [id=" + id + ", startDate=" + startDate + ", duration=" + duration + ", status=" + status
				+ ", description=" + description + ", workDissemination=" + workDissemination + ", contactDetails="
				+ contactDetails + ", comments=" + comments + ", leaveAppliedDate=" + leaveAppliedDate
				+ ", leaveApprovedDate=" + leaveApprovedDate + "]";
	}
	
}
