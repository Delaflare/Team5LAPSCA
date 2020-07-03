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
import sg.edu.LeaveApplication.service.UserService;



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
	@Autowired
	UserService uservice;
	@Autowired
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}

	
	@RequestMapping (value = "/dplist" )
		public String list(Model model)
		{
		System.out.println(dservice.findAll().size());
		model.addAttribute("dlist" , dservice.findAll());	
		
		return "/admin/departmentList";
			
		}


	@RequestMapping (value = "/adddp" )
		public String addForm(Model model)
		{
		model.addAttribute("department" ,new Department());	
		return "/admin/createDepartment";
		}
		
	@RequestMapping (value = "/editdp/{id}" )
		public String editForm(@PathVariable("id") Integer id, Model model)
		
		{
			model.addAttribute("department", dservice.findDeparmentById(id));	
			return "/admin/createDepartment";
		}
		
	@RequestMapping (value = "/savedp" )
		public String saveDepartment(@ModelAttribute ("department") 
		@Valid Department department, BindingResult bindingResult, Model model)	
		{
		if (bindingResult.hasErrors())
		{
			return "/admin/createDepartment";
		}
		else
		{
			dservice.saveDepartment(department);
		
			return "redirect:/admin/dplist";
		}
		}
		
	@RequestMapping (value = "/deletedp/{id}" )
		public String deleteDepartment(@PathVariable ("id") Integer id, Model model)
		{
			if(uservice.findUserBydepartment(dservice.findDeparmentById(id)).isEmpty()) {
				dservice.deleteDepartment(dservice.findDeparmentById(id));
			}
			else {
				model.addAttribute("msg", "Cannot delete department, please remove the users first.");
			}
		return "redirect:/admin/dplist";
		}
		
	
	
	
	
}
