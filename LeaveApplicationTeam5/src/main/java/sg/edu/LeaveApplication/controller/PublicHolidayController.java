package sg.edu.LeaveApplication.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.PublicHolidays;
import sg.edu.LeaveApplication.service.PublicHolidayService;

@Controller
@RequestMapping(value="/admin")
public class PublicHolidayController {
	
	@Autowired
	protected PublicHolidayService holiService;
	
	@RequestMapping(value="/phlist")
	public String list(Model model)
	{
		model.addAttribute("holidays",holiService.findAll());
		return "/admin/publicHolidays";
	}
	
	@RequestMapping(value="/addph")
	public String addForm(Model model) {
		model.addAttribute("holiday", new PublicHolidays());
		return "/admin/publicHolidayDetail";
	}
	
	@RequestMapping(value="/editph/{id}")
	public String editForm(Model model,@PathVariable("id") Integer id) {
		PublicHolidays holiday=holiService.findPublicHolidaysById(id);
		model.addAttribute("holiday",holiday);
		return "/admin/publicHolidayDetail";
		
	}
	
	@RequestMapping(value="/saveph")
	public String savePublicHoliday(@Valid @ModelAttribute("holiday") PublicHolidays holiday) {
		
		holiService.createPublicHoliday(holiday);
		return "forward:/public-holiday/list";
		
	}
	
	

}
