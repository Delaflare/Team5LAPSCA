package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.PublicHolidays;

public interface PublicHolidayService {
	public ArrayList<PublicHolidays> findAll();

	public boolean createPublicHoliday(PublicHolidays holiday);

	public boolean editPublicHoliday(PublicHolidays holiday);

	public void deletePublicHoliday(PublicHolidays holiday);

	public PublicHolidays findPublicHolidayByName(String name);
	
	public PublicHolidays findPublicHolidaysById(Integer id);
}
