package sg.edu.LeaveApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.OTService;

@Controller
@RequestMapping(value = "/dashboard")
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
	
	@RequestMapping(value = "/dashboard")
	public String pendingLeave(Model model)
	{
		model.addAttribute("pendingLeave", leaveservice.findAllPendingLeave());
		model.addAttribute("pendingOT", OTservice.findAllPendingOT());
		return "HomeDashboard";
	}
}
