package sg.edu.LeaveApplication.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.OTRecord;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.OTService;
import sg.edu.LeaveApplication.service.PublicHolidayService;
import sg.edu.LeaveApplication.service.UserLeaveTypesService;
import sg.edu.LeaveApplication.service.UserService;

@Controller
@RequestMapping("/leave")
public class OTController {
	
	@Autowired
	LeaveService leaveservice;
	@Autowired
	public void setLeaveservice(LeaveService leaveservice) {
		this.leaveservice = leaveservice;
	}
	@Autowired
	LeaveTypeService leavetypeservice;
	@Autowired
	public void setLeavetypeservice(LeaveTypeService leavetypeservice) {
		this.leavetypeservice = leavetypeservice;
	}
	@Autowired
	UserService uservice;
	@Autowired
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}	
	@Autowired
	PublicHolidayService holiservice;
	@Autowired
	public void setHoliservice(PublicHolidayService holiservice) {
		this.holiservice = holiservice;
	}
	@Autowired
	UserLeaveTypesService ultservice;
	@Autowired
	public void setUltservice(UserLeaveTypesService ultservice) {
		this.ultservice = ultservice;
	}
	
	@Autowired
	OTService otservice;
	@Autowired
	public void setOtservice(OTService otservice) {
		this.otservice = otservice;
	}
	
	@RequestMapping("/OTList")
	public String list(Model model) {
		
		User sessionuser = uservice.findUserById(0);
		model.addAttribute("OTBalance", ultservice.findleaveAllowance(sessionuser.getId(), "Compensation Leave"));
		model.addAttribute("OTList", otservice.findAll());
		return "OTHistory";
	}
	
	@RequestMapping("/claimOT")
	public String claimOT(Model model) {
		User sessionuser = uservice.findUserById(0);
		
		model.addAttribute("OTRecord", new OTRecord());
		model.addAttribute("OTBalance", ultservice.findleaveAllowance(sessionuser.getId(), "Compensation Leave"));
		return "OTForm";
	}
	
	@RequestMapping("/saveOT")
	public String saveOT(@ModelAttribute("OTRecord") @Valid OTRecord otrecord, BindingResult result, Model model,
			@RequestParam("startDate") String sd, @RequestParam("endDate") String ed
			) throws ParseException {
		User sessionuser = uservice.findUserById(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
		otrecord.setUser(uservice.findUserById(0));//to use session user_id
		otrecord.setSubmitDate(new Date());
		otrecord.setStartDate(LocalDate.parse(sd,formatter));
		otrecord.setEndDate(LocalDate.parse(ed,formatter));
		otrecord.setStatus(Status.PENDING);
		otservice.saveOTRecord(otrecord);
		return"redirect:/leave/OTList";
	}
	
	
	@RequestMapping("/deleteOT/{id}")
	public String deleteOT(@PathVariable("id") Integer id, Model model) {
		OTRecord ot = otservice.findById(id);
		//only when Pending, allow delete
		if(ot !=null && ot.getStatus()==Status.PENDING || ot.getStatus()==Status.UPDATED) {
			otservice.deleteOTRecord(ot);
			model.addAttribute("msg", "Leave is deleted. ");
		}
		else {
			model.addAttribute("msg", "Cannot delete leave after approved or cancelled.");
		}
		return"forward:/leave/list";
	}
}
