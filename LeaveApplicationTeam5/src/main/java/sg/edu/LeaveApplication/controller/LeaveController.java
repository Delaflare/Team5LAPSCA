package sg.edu.LeaveApplication.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		model.addAttribute("balance", 2); //will replace
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
		leaverecord.setUser(uservice.findUserById(1));//to use session user_id
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
		}
		return"forward:/leave/list";
	}

	@RequestMapping("/cancel/{id}")
	public String cancelLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		//have record and before approved, allow cancel
		if(lr !=null && lr.getStatus()!= Status.APPROVED) {
			leaveservice.cancelLeave(lr);
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
	
	@RequestMapping("/approveLeave/{id}")
	public String approveLeave(@PathVariable("id") Integer id) {
		leaveservice.Approve(id);
		return "redirect:/leave/viewLeave";
	}
	

	  @RequestMapping("/rejectLeave/{id}") 
	  public String rejectLeave(@ModelAttribute("leave") LeaveRecord leave, @PathVariable("id")Integer id, Model model) 
	  { 		  
		leave.setStatus(Status.REJECTED);
		leaveservice.saveLeave(leave);
		return "redirect:/leave/viewLeave"; 
	  }


}
