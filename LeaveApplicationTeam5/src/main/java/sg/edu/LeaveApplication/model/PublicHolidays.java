package sg.edu.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class PublicHolidays {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String holidayName;
	@NotEmpty
	private Date startDate;
	@NotEmpty
	private int duration;
	
	public PublicHolidays() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PublicHolidays(@NotEmpty String holidayName, @NotEmpty Date startDate, @NotEmpty int duration) {
		super();
		this.holidayName = holidayName;
		this.startDate = startDate;
		this.duration = duration;
	}
	public String getHolidayName() {
		return holidayName;
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
