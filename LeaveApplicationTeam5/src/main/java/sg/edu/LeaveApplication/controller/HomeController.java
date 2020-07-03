package sg.edu.LeaveApplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	    @GetMapping("index")
	    public String index(){
	        return "index";
	    }

	    @GetMapping("login")
	    public String login(){return "/login";}
	    
	    @GetMapping("admin")
	    public String testma()
	    {return "/admin/test";}
	    
	    @GetMapping("logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response) {
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	          if (auth != null) {
	              new SecurityContextLogoutHandler().logout(request, response, auth);
	          }
	    	return"redirect:/login?logout";
	    }
	    
}