package sg.edu.LeaveApplication.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.repo.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	LeaveRepository leaverepo;
	
	
	
	@Override
	public boolean saveLeave(LeaveRecord leaverecord) {
		if(leaverepo.save(leaverecord) != null) return true; 
		else return false;
	}
	
	@Override
	public ArrayList<LeaveRecord> findAll(){
		ArrayList<LeaveRecord> leavelist = (ArrayList<LeaveRecord>) leaverepo.findAll();
		return leavelist;
	}
	
	

	@Override
	public void deleteLeave(LeaveRecord lr) {
		lr.setStatus(Status.DELETED);
		leaverepo.save(lr);
		
	}
	@Override
	public void cancelLeave(LeaveRecord lr) {
		lr.setStatus(Status.CANCELLED);
		leaverepo.save(lr);
	}
	
	@Override
	public ArrayList<LeaveRecord> findAllPendingLeave(Integer reportToId) {
		ArrayList<LeaveRecord> list = (ArrayList<LeaveRecord>) leaverepo.findAllPendingLeave(reportToId);
		return list;
	}	
	@Override
	public LeaveRecord findLeaveRecordById(Integer id) {
		return leaverepo.findById(id).get();
	}
	
	@Override
	public boolean Approve(Integer id, String comment) {
		LeaveRecord leave = findLeaveRecordById(id);
		if(leave != null) {
			leave.setStatus(Status.APPROVED);
			leave.setComments(comment);
			leave.setLeaveApprovedDate(new Date());
			leaverepo.save(leave);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean Reject(Integer id, String comment) {
		LeaveRecord leave = findLeaveRecordById(id);
		if(leave != null) {
			leave.setStatus(Status.REJECTED);
			leave.setComments(comment);
			leave.setLeaveApprovedDate(new Date());
			leaverepo.save(leave);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> findAllLeaveStatusName() {
		return leaverepo.findAllLeaveStatus();
	}

	@Override
	public ArrayList<LeaveRecord> findLeaveByEmployeeAndLeave(String keyword, String ltName, Integer reportToId) {
		return leaverepo.findLeaveByEmployeeAndLeave(keyword, ltName, reportToId);
		
	}

	@Override
	public ArrayList<LeaveRecord> findLeaveHistoryByDate(String keyword, LocalDate startDate, LocalDate endDate, String ltName,
			Integer int_status, Integer reportToId) {
		return leaverepo.findLeaveHistoryByDate(keyword,startDate,endDate, ltName,int_status, reportToId);
	}
	
	@Override
	public ArrayList<LeaveRecord> findLeaveHistory(String keyword, String ltName,
			Integer int_status,Integer reportToId) {
		return leaverepo.findLeaveHistory(keyword,ltName,int_status, reportToId);
	}
	
	@Override
	public ArrayList<LeaveRecord> findOnLeave() {
		return leaverepo.findOnLeave();
	}
	
	@Override
	public ArrayList<LeaveRecord> findByUser(User user) {
		return leaverepo.findByUser(user);
	}
	
	@Override
	public ArrayList<LeaveRecord> findAllByReportTo(Integer reportToId){
		ArrayList<LeaveRecord> leavelist = (ArrayList<LeaveRecord>) leaverepo.findAllByReportTo(reportToId);
		return leavelist;
	}
	
	@Override
	public ArrayList<LeaveRecord> findUserCompensationLeave(User user) {
		return leaverepo.findByUserAndLeaveName(user, "Compensation Leave");	
	}
}
