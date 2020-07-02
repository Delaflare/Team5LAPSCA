package sg.edu.LeaveApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.OTService;
import sg.edu.LeaveApplication.service.UserLeaveTypesService;
import sg.edu.LeaveApplication.service.UserService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	LeaveService leaveservice;
	@Autowired
	public void setLeaveservice(LeaveService leaveservice) {
		this.leaveservice = leaveservice;
	}
	
	@Autowired
	OTService OTservice;
	@Autowired
	public void setOTservice(OTService OTservice) {
		this.OTservice = OTservice;
	}
	
	@Autowired
	UserService uservice;
	@Autowired
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}	
	
	@Autowired
	UserLeaveTypesService ultservice;
	@Autowired
	public void setultservice(UserLeaveTypesService ultservice) {
		this.ultservice = ultservice;
	}
	
	@RequestMapping("/home")
	public String pendingLeave(Model model)
	{
		User currentUser = uservice.findUserById(45);
		model.addAttribute("leaveRemaining", ultservice.findAllByUser(currentUser));
		model.addAttribute("onleave", leaveservice.findOnLeave());
		model.addAttribute("myleave", leaveservice.findByUser(currentUser));
		model.addAttribute("myOT", OTservice.findByUser(currentUser));
		model.addAttribute("pendingLeave", leaveservice.findAllPendingLeave());
		model.addAttribute("pendingOT", OTservice.findAllPendingOT());
	
		return "HomeDashboard";
	}

}
