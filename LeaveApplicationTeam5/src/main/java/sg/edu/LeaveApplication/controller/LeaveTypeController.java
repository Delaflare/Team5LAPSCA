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
@RequestMapping(value = "/leavetypes")
public class LeaveTypeController {

	@Autowired
	private LeaveTypeService leavetypeservice;

	@Autowired
	public void setLeaveTypeService(LeaveTypeServiceImpl leavetypeserviceImpl) {
		this.leavetypeservice = leavetypeserviceImpl;
	}

	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("ltypelist", leavetypeservice.findAll());
		return "/Admin/LeaveTypes";
	}

	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		model.addAttribute("leavetype", new LeaveTypes());
		return "/Admin/LeaveTypesDetail";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leavetype", leavetypeservice.findLeaveTypesById(id));
		return "LeaveTypesDetail";
	}

	@RequestMapping(value = "/save")
	public String saveLeaveType(@ModelAttribute("leavetype") @Valid LeaveTypes leavetypes, BindingResult bindingResult,
			Model model) {
		//if (bindingResult.hasErrors()) {
		//	return "LeaveTypesDetails";
		//}
		leavetypeservice.saveLeaveType(leavetypes);
		return "forward:/leavetypes/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id) {
		leavetypeservice.deleteLeaveType(leavetypeservice.findLeaveTypesById(id));
		return "forward:/leavetypes/list";
	}

}
