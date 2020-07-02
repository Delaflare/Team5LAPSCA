package sg.edu.LeaveApplication.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import sg.edu.LeaveApplication.model.PublicHolidays;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.Time;
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
		
		User sessionuser = uservice.findUserById(21);
		model.addAttribute("OTBalance", ultservice.findleaveAllowance(sessionuser.getId(), "Compensation Leave"));
		model.addAttribute("OTList", otservice.findAll());
		return "OTHistory";
	}
	
	@RequestMapping("/claimOT")
	public String claimOT(Model model) {
		User sessionuser = uservice.findUserById(21);
		
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
	
	
	// check balance function
		public Boolean isBalanceEnough(User user, String leaveName, Integer leaveCost) {

			if (ultservice.findleaveAllowance(user.getId(), leaveName) >= leaveCost) {
				return true;
			}
			return false;
		}

		// to move to leave service after done
		public static Integer getLeaveDayCost(String sd, String ed, List<LocalDate> holidays, Time st, Time et) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date1 = LocalDate.parse(sd, formatter);
			LocalDate date2 = LocalDate.parse(ed, formatter);

			Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
					&& date.getDayOfWeek() == DayOfWeek.SUNDAY;

			Predicate<LocalDate> isHoliday = date -> holidays.contains(date);
			long daysBetween = ChronoUnit.DAYS.between(date1, date2) + 1;

			long leaveCost = Stream.iterate(date1, date -> date.plusDays(1)).limit(daysBetween)
					.filter(isHoliday.or(isWeekend).negate()).count() * 8;
			
			
			//check if half-day applies
			if(st == Time.PM) {
				if(date1.getDayOfWeek() != DayOfWeek.SATURDAY || date1.getDayOfWeek() !=
						DayOfWeek.SUNDAY || !holidays.contains(date1) ) 
				{ leaveCost -= 4; } 
			}
			if(et == Time.AM) {
				if(date2.getDayOfWeek() != DayOfWeek.SATURDAY ||date2.getDayOfWeek()
						!= DayOfWeek.SUNDAY || !holidays.contains(date2)) 
				{leaveCost -= 4; 
				}
			}
		
			return (int) leaveCost;
		}

		@RequestMapping("/compleave/listall")
		public String listAll(Model model) {
			model.addAttribute("leaveList", leaveservice.findAll());
			return "compleaveList";
		}

		@RequestMapping("/compleave/apply")
		public String applyForm(Model model) {
			//replace once user session is ready
			User sessionUser = uservice.findUserById(21);
			
			model.addAttribute("leave", new LeaveRecord());
			model.addAttribute("leaveTypes", leavetypeservice.findAll());
			model.addAttribute("phlist", holiservice.findAll());
			model.addAttribute("balanceList", ultservice.findAllByUser(sessionUser));	
			return "createCompLeave";

		}

		@RequestMapping("/compleave/save")
		public String saveForm(@ModelAttribute("leave") @Valid LeaveRecord leaverecord, BindingResult bindingResult,
				Model model, @RequestParam("startDate") String sd, @RequestParam("endDate") String ed, @RequestParam("startTime") Time st, @RequestParam("endTime") Time et )
				throws ParseException {

			// replace when session is ready
			User sessionUser = uservice.findUserById(21);

			String leaveType = "Compensation Leave";

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			// calculate duration
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ed);
			long time = date2.getTime() - date1.getTime();
			Double duration = (double) time / (1000 * 3600 * 24) + 1;
			if (st == Time.PM) {
				duration -= 0.5;
			}
			if(et == Time.AM)
			{
				duration -= 0.5;  
			}
			Integer hrDuration = (int) (duration * 8);
			System.out.println("st: " + st);
			System.out.println("et: " + et);
			System.out.println("duration: " +duration);
			System.out.println("hrduration: " +hrDuration);
			// get compensation leave balance
			Integer balance = ultservice.findleaveAllowance(sessionUser.getId(), leaveType);
			// calculate leave cost = duration - weekend - PH
			List<PublicHolidays> holidays = holiservice.findAll();
			List<LocalDate> allPHdays = holidays.stream().map(PublicHolidays::getDate).collect(Collectors.toList());
			Integer leaveCost = getLeaveDayCost(sd, ed, allPHdays, st, et);
			System.out.println("leaveCost: " + leaveCost);
			
			if (duration <= 14) {
					//validate balance
					if(isBalanceEnough(sessionUser, leaveType,leaveCost)) {
						//set balance - leaveCost
						ultservice.update(sessionUser, leaveType, balance-leaveCost);
						leaverecord.setLeaveDayCost(leaveCost);
					}
					else {
						model.addAttribute("msg", "You do not have enough balance.");
						return "redirect:/leave/compleave/apply";
					}
			    }
		    else {
		    	
		    	//validate balance
		    	if(isBalanceEnough(sessionUser, leaveType,hrDuration)) {
		    		//balance - duration
					ultservice.update(sessionUser, leaveType, balance-hrDuration);
					leaverecord.setLeaveDayCost(hrDuration);
				}
				else {
					model.addAttribute("msg", "You do not have enough balance.");
					return "redirect:/leave/compleave/apply";
				}
		    }

			leaverecord.setLeaveTypes(leavetypeservice.findLeaveTypesByName(leaveType));
			leaverecord.setUser(uservice.findUserById(0));// to use session user_id
			leaverecord.setLeaveAppliedDate(new Date());
			leaverecord.setDuration(duration);
			leaverecord.setStartDate(LocalDate.parse(sd, formatter));
			// if new record, set to Pending; otherwise as Updated
			if (leaverecord.getStatus() == null) {
				leaverecord.setStatus(Status.PENDING);
			} else {
				leaverecord.setStatus(Status.UPDATED);
			}
			leaveservice.saveLeave(leaverecord);
			
			System.out.println("st: " + st);
			System.out.println("et: " + et);
			System.out.println("duration: " +duration);
			
			System.out.println(leaverecord.getLeaveTypes());

			return "redirect:/leave/compleave/listall";
		}
		
		@RequestMapping("/compleave/update/{id}")
		public String updateLeave(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("leaveTypes", leavetypeservice.findAll());
			model.addAttribute("phlist", holiservice.findAll());
			LeaveRecord lr = leaveservice.findLeaveRecordById(id);
			//only when Pending, allow update
			if(lr != null && lr.getStatus()==Status.PENDING || lr.getStatus()==Status.UPDATED) {
				lr.setStatus(Status.UPDATED);
				model.addAttribute("leave", lr);
				return"createCompLeave";
			}
			return "redirect:/leave/compleave/list";
		}
		
		@RequestMapping("leave/detail/{id}")
		public String viewLeave(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("leave", leaveservice.findLeaveRecordById(id));
			return"leaveDetails";
		}

		@RequestMapping("/managerViewOT")
		public String managerOTList(Model model, String keyword) {
			if(keyword !=null) {
				model.addAttribute("OTList", otservice.findPendingOTbyUser(keyword));
				System.out.print(keyword);
			}
			model.addAttribute("OTList", otservice.findAllPendingAndUpdatedOT());
			return "managerOTList";		
		}
		
		@RequestMapping("/managerOTdetails/{id}")
		public String showLeaveDetails(@PathVariable("id") Integer id, Model model) {
			model.addAttribute("OT", otservice.findById(id));
			return "managerOTDetails";
		}
		
		
		@RequestMapping("/managerOTsubmit/{id}")
		public String submit(@ModelAttribute("OT") OTRecord ot, @PathVariable("id") Integer id,
				 @RequestParam("status") Status status) {
			OTRecord newOT = otservice.findById(ot.getId());
			newOT.setStatus(status);
			//add OT hours to leave allowance if approved
			if(status.equals(status.APPROVED)){
				int addback = newOT.getTotalOTTime();
				int userId = newOT.getUser().getId();
				String leaveName = "Compensation Leave";
				int leaveAllowance = ultservice.findleaveAllowance(userId, leaveName);
				int newAllowance = leaveAllowance + addback;
				ultservice.update(newOT.getUser(), leaveName, newAllowance);
			}	
			otservice.saveOTRecord(newOT);
			


			return "redirect:/leave/managerViewOT";
		}

}
