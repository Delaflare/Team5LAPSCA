package sg.edu.LeaveApplication.controller;

import java.security.Principal;
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

import javax.mail.Session;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.model.PublicHolidays;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.service.LeaveService;
import sg.edu.LeaveApplication.service.LeaveTypeService;
import sg.edu.LeaveApplication.service.PublicHolidayService;
import sg.edu.LeaveApplication.service.UserLeaveTypesService;
import sg.edu.LeaveApplication.service.UserService;

@Controller
public class LeaveController {
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

	// return leaveDayCost upon cancel/delete/reject
	public void returnLeave(LeaveRecord lr) {
		String leaveName = lr.getLeaveTypes().getLeaveName();
		int currentBalance = ultservice.findleaveAllowance(lr.getUser().getId(), leaveName);
		ultservice.update(lr.getUser(), leaveName, currentBalance + lr.getLeaveDayCost());
	}

	// check balance function
	public Boolean isBalanceEnough(User user, String leaveName, Integer leaveCost) {

		if (ultservice.findleaveAllowance(user.getId(), leaveName) >= leaveCost) {
			return true;
		}
		return false;
	}

	// to move to leave service after done
	public static Integer getLeaveDayCost(String sd, String ed, List<LocalDate> holidays) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(sd, formatter);
		LocalDate date2 = LocalDate.parse(ed, formatter);

		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

		Predicate<LocalDate> isHoliday = date -> holidays.contains(date);
		long daysBetween = ChronoUnit.DAYS.between(date1, date2) + 1;

		long leaveCost = Stream.iterate(date1, date -> date.plusDays(1)).limit(daysBetween)
				.filter(isHoliday.or(isWeekend).negate()).count();
		return (int) leaveCost;
	}

	@RequestMapping("emp/list")
	public String list(Model model, Principal principal) {
		User sessionUser = uservice.findUserByName(principal.getName());
		model.addAttribute("leaveList", leaveservice.findAll());
		model.addAttribute("balanceList", ultservice.findAllByUser(sessionUser));
		return "leaveList";
	}

	@RequestMapping("emp/apply")
	public String applyLeave(Model model, Principal principal) {
		User sessionUser = uservice.findUserByName(principal.getName());

		model.addAttribute("leave", new LeaveRecord());
		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		model.addAttribute("phlist", holiservice.findAll());
		model.addAttribute("balanceList", ultservice.findAllByUser(sessionUser));
		return "createLeave";
	}

	@RequestMapping("emp/save")
	public String saveLeave(@ModelAttribute("leave") @Valid LeaveRecord leaverecord, BindingResult result, Model model,
			Principal principal, @RequestParam("startDate") String sd, @RequestParam("endDate") String ed)
			throws ParseException {

		User sessionUser = uservice.findUserByName(principal.getName());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// calculate duration
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ed);
		long time = date2.getTime() - date1.getTime();
		Integer duration = (int) time / (1000 * 3600 * 24) + 1;
		List<PublicHolidays> holidays = holiservice.findAll();
		List<LocalDate> allPHdays = holidays.stream().map(PublicHolidays::getDate).collect(Collectors.toList());
		LocalDate datestart = LocalDate.parse(sd, formatter);
		LocalDate dateend = LocalDate.parse(ed, formatter);

		// check whether need to deduct PH and weekend
		Integer balance = ultservice.findleaveAllowance(sessionUser.getId(),
				leaverecord.getLeaveTypes().getLeaveName());

		// check if start end days are holidays or nonworking days
		if (allPHdays.contains(datestart) || allPHdays.contains(dateend)
				|| datestart.getDayOfWeek() == DayOfWeek.SATURDAY || datestart.getDayOfWeek() == DayOfWeek.SUNDAY
				|| dateend.getDayOfWeek() == DayOfWeek.SATURDAY || dateend.getDayOfWeek() == DayOfWeek.SUNDAY) {
			model.addAttribute("msg",
					"Your start or end date falls on a weekend or a public holiday, please try again.");
			return "forward:/emp/apply";
		} else {

			if (duration <= 14) {
				// calculate leave cost = duration - weekend - PH
				
				Integer leaveCost = getLeaveDayCost(sd, ed, allPHdays);
				// validate balance
				if (isBalanceEnough(sessionUser, leaverecord.getLeaveTypes().getLeaveName(), leaveCost)) {
					// set balance - leaveCost
					ultservice.update(sessionUser, leaverecord.getLeaveTypes().getLeaveName(), balance - leaveCost);
					leaverecord.setLeaveDayCost(leaveCost);
				} else {
					model.addAttribute("msg", "You do not have enough balance.");
					return "forward:/emp/apply";
				}
			} else {

				// validate balance
				if (isBalanceEnough(sessionUser, leaverecord.getLeaveTypes().getLeaveName(), duration)) {
					// balance - duration
					ultservice.update(sessionUser, leaverecord.getLeaveTypes().getLeaveName(), balance - duration);
					leaverecord.setLeaveDayCost(duration);
				} else {
					model.addAttribute("msg", "You do not have enough balance.");
					return "forward:/emp/apply";
				}
			}

			leaverecord.setLeaveTypes(leaverecord.getLeaveTypes());
			leaverecord.setUser(uservice.findUserByName(principal.getName()));
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
			return "redirect:/emp/list";
		}
	}

	@RequestMapping("emp/update/{id}")
	public String updateLeave(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("leaveTypes", leavetypeservice.findAll());
		model.addAttribute("phlist", holiservice.findAll());
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		// only when Pending, allow update
		if (lr != null && lr.getStatus() == Status.PENDING || lr.getStatus() == Status.UPDATED) {
			lr.setStatus(Status.UPDATED);
			model.addAttribute("leave", lr);
			return "updateCompleave";
		}
		return "redirect:/emp/list";
	}

	@RequestMapping("emp/detail/{id}")
	public String viewLeave(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leave", leaveservice.findLeaveRecordById(id));
		return "leaveDetails";
	}

	@RequestMapping("emp/delete/{id}")
	public String deleteLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		// only when Pending, allow delete
		if (lr != null && lr.getStatus() == Status.PENDING || lr.getStatus() == Status.UPDATED) {
			returnLeave(lr);
			leaveservice.deleteLeave(lr);
			model.addAttribute("msg", "Leave is deleted. ");
			return "forward:/emp/list";
		} else {
			model.addAttribute("msg", "Cannot delete leave after approved or cancelled.");
			return "forward:/emp/list";
		}
	}

	@RequestMapping("emp/cancel/{id}")
	public String cancelLeave(@PathVariable("id") Integer id, Model model) {
		LeaveRecord lr = leaveservice.findLeaveRecordById(id);
		// have record and after approved, allow cancel
		if (lr != null && lr.getStatus() == Status.APPROVED) {
			leaveservice.cancelLeave(lr);
			returnLeave(lr);
			model.addAttribute("msg", "Leave is cancelled.");
			return "forward:/emp/list";
		} else {
			model.addAttribute("msg", "Leave can only be canceld after approved.");
			return "forward:/emp/list";
		}

	}

//breakline between emp vs mananger

	@RequestMapping("mng/viewLeave")
	public String viewLeaveRequest(Model model, Principal principal, String keyword, String ltName) {
		User manager = uservice.findUserByName(principal.getName());

		Integer reportToId = manager.getId();
		model.addAttribute("ltNames", leavetypeservice.findAllLeaveTypeNames());
		if (keyword != null || ltName != null) {
			model.addAttribute("leaveList", leaveservice.findLeaveByEmployeeAndLeave(keyword, ltName, reportToId));
		} else {
			model.addAttribute("leaveList", leaveservice.findAllPendingLeave(reportToId));
		}
		return "/manager/viewLeaveRequests";
	}

	@RequestMapping("mng/viewLeaveHistory")
	public String viewLeaveHistory(Model model, Principal principal, String keyword, String fromDate, String toDate,
			String ltName, String status) {
		User manager = uservice.findUserByName(principal.getName());

		Integer reportToId = manager.getId();
		model.addAttribute("ltNames", leavetypeservice.findAllLeaveTypeNames());
		// System.out.println("k"+keyword);System.out.println("Sd"+startDate);System.out.println("ed"+endDate);System.out.println("na"+ltName);System.out.println("status"+status);
		if ((keyword != "" && keyword != null) || // Search Part
				(ltName != "" && ltName != null) || (fromDate != "" && fromDate != null)
				|| (toDate != "" && toDate != null) || (status != "" && status != null)) {

			Integer int_status = -1;
			if (status.equals("PENDING"))
				int_status = Status.PENDING.ordinal();
			else if (status.equals("APPROVED"))
				int_status = Status.APPROVED.ordinal();
			else if (status.equals("REJECTED"))
				int_status = Status.REJECTED.ordinal();
			else if (status.equals("UPDATED"))
				int_status = Status.UPDATED.ordinal();
			else if (status.equals("CANCELLED"))
				int_status = Status.CANCELLED.ordinal();
			else if (status.equals("DELETED"))
				int_status = Status.DELETED.ordinal();

			if ((fromDate != "" && fromDate != null) || (toDate != "" && toDate != null))// Search with date
			{

				LocalDate startDate = LocalDate.parse(fromDate);
				LocalDate endDate = LocalDate.parse(toDate);
				model.addAttribute("leaveHistoryList", leaveservice.findLeaveHistoryByDate(keyword, startDate, endDate,
						ltName, int_status, reportToId));
				System.out.println(ltName);
			} else // Search Without date
			{
				model.addAttribute("leaveHistoryList",
						leaveservice.findLeaveHistory(keyword, ltName, int_status, reportToId));

			}

		} else {
			model.addAttribute("leaveHistoryList", leaveservice.findAllByReportTo(reportToId));
			System.out.println(ltName);
		}
		return "/manager/viewLeaveHistory";
	}

	@RequestMapping("mng/details/{id}")
	public String showLeaveDetails(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("leave", leaveservice.findLeaveRecordById(id));
		model.addAttribute("allowEdit",
				leaveservice.findLeaveRecordById(id).getStatus().getDisplayValue().equals("PENDING"));
		return "/manager/pendingLeaveDetails";
	}

	@RequestMapping("mng/submit/{id}")
	public String submit(@ModelAttribute("leave") LeaveRecord leave, @PathVariable("id") Integer id,
			@RequestParam("comments") String comments, @RequestParam("status") Status status) {
		LeaveRecord newLeave = leaveservice.findLeaveRecordById(leave.getId());
		newLeave.setComments(comments);
		newLeave.setStatus(status);
		if (status.equals(status.REJECTED)) {
			int addback = newLeave.getLeaveDayCost();
			int userId = newLeave.getUser().getId();
			String leaveName = newLeave.getLeaveTypes().getLeaveName();
			int leaveAllowance = ultservice.findleaveAllowance(userId, leaveName);
			int newAllowance = leaveAllowance + addback;
			ultservice.update(newLeave.getUser(), leaveName, newAllowance);
		}
		leaveservice.saveLeave(newLeave);
		SendEamilController.SendEmailSSL(status.name(),
				newLeave.getUser().getFirstName() + " " + newLeave.getUser().getLastName(), comments,
				newLeave.getUser().getEmail());
		return "redirect:/mng/viewLeave";
	}

}
