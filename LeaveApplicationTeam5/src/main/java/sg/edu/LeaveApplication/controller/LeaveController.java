package sg.edu.LeaveApplication.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.LeaveTypeService;

@Controller
@RequestMapping(value="/leave")
@SessionAttributes("user")
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

	@RequestMapping("/list")
	public String list(Model model) {
		//model.addAttribute("leaveName", leavetypeservice.findleavenameById());
		model.addAttribute("leaveList", leaveservice.findAll());
		return "leaveList";
		
	}

	@RequestMapping("/apply")
	public String applyLeave(Model model) {
		model.addAttribute("leave", new LeaveRecord());
		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		model.addAttribute("fakeleavetypes", "Annual Leave");//will replace
		model.addAttribute("balance", 2);//will replace
		model.addAttribute("userId", 2);//will replace

		return "createLeave";
	}

	@RequestMapping("/save")
	public String saveLeave(@ModelAttribute("leave") @Valid LeaveRecord leaverecord, BindingResult result, Model model, 
			@RequestParam("startDate") String sd, @RequestParam("endDate") String ed) throws ParseException {

		//calculate duration
		Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(sd);  
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ed);
		var temp = date2.getTime() - date1.getTime();
		Integer duration = (int) (temp / (1000 *3600 * 24));
		
		//valid balance
		
		leaverecord.setUserId(2);
		leaverecord.setLeaveTypeId(2);
		leaverecord.setDeptId(1);
		leaverecord.setLeaveAppliedDate(new Date());
		leaverecord.setDuration(duration);
		leaverecord.setStartDate(date1);
		leaverecord.setStatus(Status.PENDING);
		leaveservice.saveLeave(leaverecord);
		return "redirect:/leave/list";
		
	}

	@GetMapping("/approval")
	public String ApproveLeave()
	{
		
		return "managerHome";
	}
	
	public String RejectLeave()
	{
		
		
		return "managerHome";
	}
	
	public String ViewLeaveRequest()
	{
		// get the individual Leave request
		return "managerViewLeave";
	}
	
	public String GetLeaveRequest()
	{
		//print out all the leave
		return "managerLeaveList";
	}
	
}
