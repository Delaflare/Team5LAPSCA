package sg.edu.LeaveApplication.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.PublicHolidays;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.UserService;

@Controller
@SessionAttributes("user")
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	LeaveService leaveservice;
	@Autowired
	public void setLeaveservice(LeaveService leaveservice) {
		this.leaveservice = leaveservice;
	}
	
	@Autowired
	LeaveTypeService leavetypeservice;
	@Autowired
	public void setLeavetypeservice(LeaveTypeService leavetypeservice) {
		this.leavetypeservice = leavetypeservice;
	}
	
	@Autowired
	UserService uservice;
	@Autowired
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}
	/*
	@Autowired
	//get all holidays and insert as param
	
	
	public Integer getDuration(String sd, String ed, ArrayList<PublicHolidays> holidays) {
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date1= LocalDate.parse(sd, formatter);  
		LocalDate date2= LocalDate.parse(ed, formatter); 
		
		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				&& date.getDayOfWeek() == DayOfWeek.SUNDAY;
		
		//Predicate<LocalDate> isHolidy = date -> date.holidays.isPresent() ? holidays.get().contains(date): false;
		long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		long duration = Stream.iterate(sd, date->date.plusDays(1))
				.limit(daysBetween)
				.filter(isHolidy.or(isWeekend).negate().count());
		return duration;
	}
	*/
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("leaveType", leavetypeservice.findAll());
		model.addAttribute("leaveList", leaveservice.findAll());
		return "leaveList";
	}

	@RequestMapping("/apply")
	public String applyLeave(Model model) {
		
		model.addAttribute("leave", new LeaveRecord());
		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		model.addAttribute("balance", 2);//will replace
		return "createLeave";
	}

	@RequestMapping("/save")
	public String saveLeave(@ModelAttribute("leave") @Valid LeaveRecord leaverecord, BindingResult result, Model model,
			@RequestParam("startDate") String sd, @RequestParam("endDate") String ed) throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(sd);
		//calculate duration
		//Integer duration = getDuration(sd,ed);
		
		//valid balance
		leaverecord.setDuration(4);
		leaverecord.setUser(uservice.findUserById(7));//to use session user_id
		leaverecord.setLeaveAppliedDate(new Date());
		//leaverecord.setDuration(duration);
		leaverecord.setStartDate(date1);
		//if new record, set to Pending; otherwise as Updated
		if(leaverecord.getStatus() == null) {
			leaverecord.setStatus(Status.PENDING);
		}
		else {
			leaverecord.setStatus(Status.UPDATED);
		}
		
		leaveservice.saveLeave(leaverecord);
		return "redirect:/leave/list";
	}
	
	
	@RequestMapping("/update/{id}")
	public String updateLeave(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		LeaveRecord lr = leaveservice.findLeaveRecordByID(id);
		//only when Pending, allow update
		if(lr != null && lr.getStatus()==Status.PENDING || lr.getStatus()==Status.UPDATED) {
			model.addAttribute("leave", lr);
			return"createLeave";
		}
		return "redirect:/leave/list";
	}
	
	@RequestMapping("/detail/{id}")
	public String viewLeave(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leave", leaveservice.findLeaveRecordByID(id));
		return"leaveDetails";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordByID(id);
		//only when Pending, allow delete
		if(lr !=null && lr.getStatus()==Status.PENDING || lr.getStatus()==Status.UPDATED) {
			leaveservice.deleteLeave(lr);
		}
		return"forward:/leave/list";
	}

	@RequestMapping("/cancel/{id}")
	public String cancelLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordByID(id);
		//have record and before approved, allow cancel
		if(lr !=null && lr.getStatus()!= Status.APPROVED) {
			leaveservice.cancelLeave(lr);
		}
		return"forward:/leave/list";
	}

	@RequestMapping("/viewLeave")
	public String viewLeaveRequest(Model model) {
		model.addAttribute("ltNames", leavetypeservice.findAllLeaveTypeNames());
		return "viewLeaveRequests";	
	}
	
	@RequestMapping("/getLeave") 
	public String getLeaveRequest(Model model) {
	 model.addAttribute("leaveList",leaveservice.findAll());
		  return "viewLeaveRequests";	  
	}
	
	@RequestMapping("/pendingLeaveDetails/{id}")
	public String showLeaveDetails(@PathVariable("id")Integer id, Model model) {
		model.addAttribute("leaveRecord", leaveservice.findLeaveRecordById(id));
		return "pendingLeaveDetails";
	}
	
	@RequestMapping("/approveLeave/{id}")
	public String approveLeave(@PathVariable("id") Integer id) {
		leaveservice.Approve(id);
		return "redirect:/leave/getLeave";
	}
	
	@RequestMapping("/rejectLeave")
	public String rejectLeave(@PathVariable("id") Integer id) {
		leaveservice.Reject(id);
		return "redirect:/leave/getLeave;";
	}
	

}
