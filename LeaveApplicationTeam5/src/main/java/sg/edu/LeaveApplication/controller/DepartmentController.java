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
import sg.edu.LeaveApplication.service.DepartmentService;
import sg.edu.LeaveApplication.service.DepartmentServiceImpl;



@Controller
@RequestMapping (value = "/admin" )
public class DepartmentController {
	
	@Autowired
	private DepartmentService dservice;
	
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl dserviceImpl)
	{
		this.dservice = dserviceImpl;
	}

	
	@RequestMapping (value = "/dplist" )
		public String list(Model model)
		{
		System.out.println(dservice.findAll().size());
		model.addAttribute("dlist" , dservice.findAll());	
		
		return "/admin/department-list";
			
		}
	
	@RequestMapping (value = "/adddp" )
		public String addForm(Model model)
		{
		model.addAttribute("department" ,new Department());	
		return "/admin/department-form";
		}
		
	@RequestMapping (value = "/editdp/{id}" )
		public String editForm(@PathVariable("id") Integer id, Model model)
		
		{
		model.addAttribute("department", dservice.findDeparmentById(id));	
		return "/admin/department-form";
		}
		
	@RequestMapping (value = "/savedp" )
		public String saveDepartment(@ModelAttribute ("department") 
		@Valid Department department, BindingResult bindingResult, Model model)
		// @valid is done to make sure there is a valid department, and must
		// do the binding result immediately after the object u wanna bind
	
		{
		if (bindingResult.hasErrors())
		{
			return "/admin/department-form";
		}
		else
		{
			dservice.saveDepartment(department);
		
			return "forward:/dept/list";
		}
		}
		
	@RequestMapping (value = "/deletedp/{id}" )
		public String deleteDepartment(@PathVariable ("id") Integer id)
		{
		dservice.deleteDepartment(dservice.findDeparmentById(id));
		
		return "forward:/dept/list";
		}
		
	
	
	
	
}
