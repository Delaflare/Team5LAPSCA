package sg.edu.LeaveApplication.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.LeaveTypeServiceImpl;
import sg.edu.LeaveApplication.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class LeaveTypeController {

	@Autowired
	private LeaveTypeService leavetypeservice;

	@Autowired
	public void setLeaveTypeService(LeaveTypeServiceImpl leavetypeserviceImpl) {
		this.leavetypeservice = leavetypeserviceImpl;
	}

	@Autowired
	UserService uservice;
	@Autowired
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}	
	
	@RequestMapping(value = "/leavetypelist")
	public String list(Model model, Principal principal) {
		model.addAttribute("userRole", uservice.findUserByName(principal.getName()).getRole());		
		model.addAttribute("ltypelist", leavetypeservice.findAll());
		return "/admin/LeaveTypes";//view
	}

	@RequestMapping(value = "/addleavetype")
	public String addForm(Model model, Principal principal) {
		model.addAttribute("leavetype", new LeaveTypes());
		model.addAttribute("userRole", uservice.findUserByName(principal.getName()).getRole());
		return "/admin/LeaveTypesDetail";
	}

	@RequestMapping(value = "/editleavetype/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model, Principal principal) {
		model.addAttribute("userRole", uservice.findUserByName(principal.getName()).getRole());
		model.addAttribute("leavetype", leavetypeservice.findLeaveTypesById(id));
		return "/admin/LeaveTypesDetail";
	}

	@RequestMapping(value = "/saveleavetype")
	public String saveLeaveType(@ModelAttribute("leavetype") @Valid LeaveTypes leavetypes, BindingResult bindingResult,
			Model model) {
		leavetypeservice.saveLeaveType(leavetypes);
		return "forward:/admin/leavetypelist";
	}

	@RequestMapping(value = "/deleteleavetype/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id) {
		leavetypeservice.deleteLeaveType(leavetypeservice.findLeaveTypesById(id));
		return "forward:/admin/leavetypelist";
	}

}
