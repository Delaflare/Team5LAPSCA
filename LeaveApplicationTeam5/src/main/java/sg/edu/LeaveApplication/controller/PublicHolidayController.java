package sg.edu.LeaveApplication.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.validation.Valid;

import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.PublicHolidays;
import sg.edu.LeaveApplication.service.PublicHolidayService;

@Controller
@RequestMapping(value="/public-holiday")
public class PublicHolidayController {
	
	@Autowired
	protected PublicHolidayService holiService;
	
	@RequestMapping(value="/list")
	public String list(Model model)
	{
		model.addAttribute("holidays",holiService.findAll());
		return "publicHolidays";
	}
	
	@RequestMapping(value="/add")
	public String addForm(Model model) {
		model.addAttribute("holiday", new PublicHolidays());
		return "publicHolidayDetail";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editForm(Model model,@PathVariable("id") Integer id) {
		PublicHolidays holiday=holiService.findPublicHolidaysById(id);
		model.addAttribute("holiday",holiday);
		return "publicHolidayDetail";
		
	}
	
	@RequestMapping(value="/save")
	public String savePublicHoliday(@Valid @ModelAttribute("holiday") PublicHolidays holiday) {
		
		Instant instant = holiday.getStartDate().toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();
		LocalDate local_end_date =  date.plusDays(holiday.getDuration());
		Date end_date = Date.from(local_end_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		holiday.setEndDate(end_date);
		holiService.createPublicHoliday(holiday);
		return "forward:/public-holiday/list";
		
	}
	
	

}
