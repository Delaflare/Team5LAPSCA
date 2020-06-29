package sg.edu.LeaveApplication.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.Department;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.service.DepartmentService;
import sg.edu.LeaveApplication.service.DepartmentServiceImpl;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.LeaveServiceImpl;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.LeaveTypeServiceImpl;
import sg.edu.LeaveApplication.service.UserService;
import sg.edu.LeaveApplication.service.UserServiceImpl;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService uservice;
	@Autowired
	private DepartmentService dservice;
	@Autowired
	private LeaveService lservice;
	@Autowired
	private LeaveTypeService ltypeservice;
	
	@Autowired
	public void setUserService(UserServiceImpl userviceImpl,
			DepartmentServiceImpl dserviceImpl,
			LeaveServiceImpl lserviceImpl, LeaveTypeServiceImpl ltypeserviceImpl) {
		this.uservice = userviceImpl;
		this.dservice = dserviceImpl;
		this.lservice = lserviceImpl;
		this.ltypeservice = ltypeserviceImpl;
	}
	
	@RequestMapping(value = "/list")
	public String listAll(Model model) {
		model.addAttribute("users", uservice.findAll());
		return "userProfile";
	}
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("dlist" , dservice.findAllDepartmentNames());	
		//System.out.print(dservice.findAll().size());
		return "createUserForm";
	}
	
	@RequestMapping(value = "/save")
	public String saveUser(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, Model model) {
		Department d = dservice.findDeparmentByName(user.getDepartment().getName());
		user.setDepartment(d);
		if(bindingResult.hasErrors())
			return "createUserForm"; 
		uservice.saveUser(user);
		return "forward:/user/list";
	}

	@GetMapping("/edit/{id}")
	public String editForm(Model model, @PathVariable("id") Integer id) {	
		model.addAttribute("user", uservice.findUserById(id));
		return "createUserForm";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Integer id) {
		User user = uservice.findUserById(id);
		uservice.deleteUser(user);
		return "forward:/user/list";
	}
	
	@GetMapping("/display/{id}")
	public String displayUser(Model model, @PathVariable("id") Integer id) {
		User user = uservice.findUserById(id);
		model.addAttribute("user", user);
		return "userRecord";
	}	
	
	@GetMapping("/assignLeave/{id}")
	public String assignLeave(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("leaveTypes", ltypeservice.findAll());
		model.addAttribute("user", uservice.findUserById(id));
		System.out.print(ltypeservice.findAll());
		//model.addAttribute("leavelist", lservice.findAll());		
		return "assign-leave";
	}
	
}
