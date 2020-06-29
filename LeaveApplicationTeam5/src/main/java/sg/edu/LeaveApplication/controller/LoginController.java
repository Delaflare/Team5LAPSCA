package sg.edu.LeaveApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping("/login")
	public String Login(@RequestParam("userName") String userName, @RequestParam("password") String password)
	
	{
		String a = "a";
		return a;
	}
}
