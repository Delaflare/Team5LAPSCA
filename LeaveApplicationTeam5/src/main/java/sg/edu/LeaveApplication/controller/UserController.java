package sg.edu.LeaveApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.repo.UserRepository;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserRepository urepo;
	
	@RequestMapping(value = "/list")
	public String listAll(Model model) {
		model.addAttribute("users", urepo.findAll());
		return "userProfile";
	}
	
	@RequestMapping(value = "/add")
	public String addForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "createUserForm";
	}
	
	@RequestMapping(value = "/save")
	public String saveUser(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "createUserForm";
		urepo.save(user);
		return "forward:/user/list";
	}

	@GetMapping("/edit/{id}")
	public String editForm(Model model, @PathVariable("id") Integer id) {	
		model.addAttribute("user", urepo.findById(id).get());
		return "createUserForm";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") Integer id) {
		User user = urepo.findById(id).get();
		urepo.delete(user);
		return "forward:/user/list";
	}
	
}
