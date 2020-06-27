package sg.edu.LeaveApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.LeaveApplication.model.Department;
import sg.edu.LeaveApplication.service.DepartmentService;
import sg.edu.LeaveApplication.service.DepartmentServiceImpl;

public class DepartmentController {
	
	@Autowired
	private DepartmentService dservice;
	
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl dserviceImpl)
	{
		this.dservice = dserviceImpl;
	}

	
	@RequestMapping (value = "/list" )
		public String list(Model model)
		{
		System.out.println(dservice.findAll().size());
		model.addAttribute("dlist" , dservice.findAll());	
		
		return "department-list";
			
		}
	
	@RequestMapping (value = "/add" )
		public String addForm(Model model)
		{
		model.addAttribute("department" ,new Department());	
		return "department-form";
		}
		
	@RequestMapping (value = "/edit/{id}" )
		public String editForm(@PathVariable("id") Integer id, Model model)
		
		{
		model.addAttribute("department", dservice.findDeparmentById(id));	
		return "department-form";
		}
		
	@RequestMapping (value = "/save" )
		public String saveDepartment(@ModelAttribute ("department") 
		@Valid Department department, BindingResult bindingResult, Model model)
		// @valid is done to make sure there is a valid department, and must
		// do the binding result immediately after the object u wanna bind
	
		{
		if (bindingResult.hasErrors())
		{
			return "department-form";
		}
		else
		{
			dservice.saveDepartment(department);
		
			return "forward:/dept/list";
		}
		}
		
	@RequestMapping (value = "/delete/{id}" )
		public String deleteDepartment(@PathVariable ("id") Integer id)
		{
		dservice.deleteDepartment(dservice.findDeparmentById(id));
		
		return "forward:/dept/list";
		}
		
	
	
	
	
}
