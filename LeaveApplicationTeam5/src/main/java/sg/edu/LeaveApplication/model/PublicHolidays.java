package sg.edu.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PublicHolidays {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	//@NotEmpty
	private String holidayName;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date endDate;
	//@NotEmpty
	private int duration;
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public PublicHolidays() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PublicHolidays(@NotEmpty String holidayName, @NotEmpty Date startDate, @NotEmpty int duration) {

		super();
		this.id = id;
		this.holidayName = holidayName;
		this.startDate = startDate;
		this.duration = duration;
		this.endDate = endDate;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
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
}
