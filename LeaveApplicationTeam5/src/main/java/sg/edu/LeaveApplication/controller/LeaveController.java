package sg.edu.LeaveApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Leave")
@SessionAttributes("user")
public class LeaveController {
	
	
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
