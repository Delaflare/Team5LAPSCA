package sg.edu.LeaveApplication.controller;

import java.security.Principal;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.PublicHolidays;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.service.PublicHolidayService;
import sg.edu.LeaveApplication.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class PublicHolidayController {
	
	@Autowired
	protected PublicHolidayService holiService;
	
	@Autowired
	UserService uservice;

	@Autowired
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}

	
	@RequestMapping(value="/phlist")
	public String list(Model model, Principal principal)
	{
		
		User sessionUser = uservice.findUserByName(principal.getName());		
		boolean isLoggedIn = false;
		if (principal != null) {isLoggedIn = true;}
		model.addAttribute("isLoggedIn", isLoggedIn);
		model.addAttribute("isManager", sessionUser.getRole().equals("MANAGER"));
		model.addAttribute("isAdmin", sessionUser.getRole().equals("ADMIN"));
		
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
		return "forward:/admin/phlist";
		
	}
	
	@RequestMapping(value = "/deleteph/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id) {
		holiService.deletePublicHoliday(holiService.findPublicHolidaysById(id));
		return "forward:/admin/phlist";
	}

}
