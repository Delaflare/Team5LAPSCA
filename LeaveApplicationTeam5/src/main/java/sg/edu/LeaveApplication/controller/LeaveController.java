package sg.edu.LeaveApplication.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.PublicHolidayService;
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
	
	@Autowired
	PublicHolidayService holiservice;
	@Autowired
	public void setHoliservice(PublicHolidayService holiservice) {
		this.holiservice = holiservice;
	}
	
	//to move to leave service after done
	public static Integer getLeaveCost(String sd, String ed, List<LocalDate> holidays) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1= LocalDate.parse(sd, formatter);  
		LocalDate date2= LocalDate.parse(ed, formatter); 
		
		Predicate<LocalDate> isWeekend = date->date.getDayOfWeek() == DayOfWeek.SATURDAY
				&& date.getDayOfWeek() == DayOfWeek.SUNDAY;
		
		Predicate<LocalDate> isHoliday = date->holidays.contains(date);
		long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		
		long duration = Stream.iterate(date1, date->date.plusDays(1))
				.limit(daysBetween)
				.filter(isHoliday.or(isWeekend).negate())
				.count();
		
		return (int)duration;
	}
		

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("leaveList", leaveservice.findAll());
		return "leaveList";
	}

	@RequestMapping("/apply")
	public String applyLeave(Model model) {
		
		model.addAttribute("leave", new LeaveRecord());
		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		model.addAttribute("balance", 200); //will replace once balance is ready
		//model.addAttribute("balance", uservice.findBalanceById());
		return "createLeave";
	}

	@RequestMapping("/save")
	public String saveLeave(@ModelAttribute("leave") @Valid LeaveRecord leaverecord, BindingResult result, Model model,
			@RequestParam("startDate") String sd, @RequestParam("endDate") String ed) throws ParseException {
	 
		//calculate duration
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ed);
		
	    long time = date2.getTime() - date1.getTime();
	    int duration = (int)time/(1000*3600*24) +1;
	    //check PH and weekend
	    if(duration <= 14) {
	    	//calculate leave cost = duration - weekend - PH
			List<LocalDate> holidays = new ArrayList<>();
		    holidays.add(LocalDate.of(2020, 7, 1));
		    holidays.add(LocalDate.of(2020, 7, 3));
			Integer leaveCost = getLeaveCost(sd, ed, holidays);
			//set balance - leaveCost
	    }
	    else {
	    	//balance - duration
	    }
		//valid balance
	    
		leaverecord.setLeaveTypes(leavetypeservice.findLeaveTypesById(5));
		leaverecord.setUser(uservice.findUserById(1));//to use session user_id
		leaverecord.setLeaveAppliedDate(new Date());
		leaverecord.setDuration(duration);
		leaverecord.setStartDate(date1);
		
		//if new record, set to Pending; otherwise as Updated
		if(leaverecord.getStatus() == null) {
			leaverecord.setStatus(Status.PENDING);
			leaveservice.saveLeave(leaverecord);
		}
		else 
		{
			leaverecord.setStatus(Status.UPDATED);
			leaveservice.saveLeave(leaverecord);
		}
		
		return "redirect:/leave/list";
	}
		
	@RequestMapping("/update/{id}")
	public String updateLeave(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		//only when Pending, allow update
		if(lr != null && lr.getStatus()==Status.PENDING || lr.getStatus()==Status.UPDATED) {
			model.addAttribute("leave", lr);
			return"createLeave";
		}
		return "redirect:/leave/list";
	}
	
	@RequestMapping("/detail/{id}")
	public String viewLeave(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leave", leaveservice.findLeaveRecordById(id));
		return"leaveDetails";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		//only when Pending, allow delete
		if(lr !=null && lr.getStatus()==Status.PENDING || lr.getStatus()==Status.UPDATED) {
			leaveservice.deleteLeave(lr);
			model.addAttribute("msg", "Leave is deleted.");
		}
		else {
			model.addAttribute("msg", "Cannot delete leave after approved or cancelled.");
		}
		return"forward:/leave/list";
	}
	
	@RequestMapping("/cancel/{id}")
	public String cancelLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		//have record and after approved, allow cancel
		if(lr !=null && lr.getStatus() == Status.APPROVED) {
			leaveservice.cancelLeave(lr);
			model.addAttribute("msg", "Leave is cancelled.");
		}
		else {
			model.addAttribute("msg", "Leave cannot be canceld after approved.");
		}
		return"forward:/leave/list";
	}

	@RequestMapping("/viewLeave")
	public String viewLeaveRequest(Model model) {
		model.addAttribute("ltNames", leavetypeservice.findAllLeaveTypeNames());
		model.addAttribute("leaveList",leaveservice.findAllPendingLeave());
		return "viewLeaveRequests";	
	}
	
	@RequestMapping("/getLeave") 
	public String getLeaveRequest(Model model) {
	 model.addAttribute("leaveList",leaveservice.findAllPendingLeave());
		  return "viewLeaveRequests";	  
	}
	
	@RequestMapping("/details/{id}")
	public String showLeaveDetails(@PathVariable("id")Integer id, Model model) {
		model.addAttribute("leave", leaveservice.findLeaveRecordById(id));
		return "pendingLeaveDetails";
	}
	
	@RequestMapping("/approveLeave/{id}/{comment}")
	public String approveLeave(@PathVariable("id") Integer id, @PathVariable("comment") String comment) {
		leaveservice.Approve(id, comment);
		return "redirect:/leave/viewLeave";
	}
	
	
	@RequestMapping("/rejectLeave/{id}/{comment}")
	public String rejectLeave(@PathVariable("id") Integer id, @PathVariable("comment") String comment) {
		leaveservice.Reject(id,comment);
		return "redirect:/leave/viewLeave";
	}

	/*
	 * @RequestMapping("/submit/{id}") public String submit(@ModelAttribute("leave")
	 * LeaveRecord leave, @PathVariable("id")Integer id ) { leave =
	 * leaveservice.findLeaveRecordById(id); leaveservice.saveLeave(leave); return
	 * "redirect:/leave/viewLeave"; }
	 */
}
