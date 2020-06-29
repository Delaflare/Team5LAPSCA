package sg.edu.LeaveApplication.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PublicHolidays {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private int id;
	//@NotEmpty
	private String holidayName;
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	//@NotEmpty
	//private int duration;
	
	
	public PublicHolidays() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PublicHolidays(int id, String holidayName, LocalDate date) {
		super();
		this.id = id;
		this.holidayName = holidayName;
		this.date = date;
	}

	public String getHolidayName() {
		return holidayName;
	}
	public int getId() {
		return id;
	}
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
}
