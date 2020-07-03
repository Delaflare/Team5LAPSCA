package sg.edu.LeaveApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.Department;
import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.LeaveTypeServiceImpl;

@Controller
@RequestMapping(value = "/admin")
public class LeaveTypeController {

	@Autowired
	private LeaveTypeService leavetypeservice;

	@Autowired
	public void setLeaveTypeService(LeaveTypeServiceImpl leavetypeserviceImpl) {
		this.leavetypeservice = leavetypeserviceImpl;
	}

	@RequestMapping(value = "/leavetypelist")
	public String list(Model model) {
		model.addAttribute("ltypelist", leavetypeservice.findAll());
		return "/admin/LeaveTypes";
	}

	@RequestMapping(value = "/addleavetype")
	public String addForm(Model model) {
		model.addAttribute("leavetype", new LeaveTypes());
		return "/admin/LeaveTypesDetail";
	}

	@RequestMapping(value = "/editleavetype/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leavetype", leavetypeservice.findLeaveTypesById(id));
		return "/admin/LeaveTypesDetail";
	}

	@RequestMapping(value = "/saveleavetype")
	public String saveLeaveType(@ModelAttribute("leavetype") @Valid LeaveTypes leavetypes, BindingResult bindingResult,
			Model model) {
		//if (bindingResult.hasErrors()) {
		//	return "LeaveTypesDetails";
		//}
		leavetypeservice.saveLeaveType(leavetypes);
		return "forward:/leavetypes/list";
	}

	@RequestMapping(value = "/deleteleavetype/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id) {
		leavetypeservice.deleteLeaveType(leavetypeservice.findLeaveTypesById(id));
		return "forward:/leavetypes/list";
	}

}
