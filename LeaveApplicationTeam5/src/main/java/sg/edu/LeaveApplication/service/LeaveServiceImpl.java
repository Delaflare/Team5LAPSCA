package sg.edu.LeaveApplication.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.model.Status;
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
		leaverepo.delete(lr);
	}
	@Override
	public void cancelLeave(LeaveRecord lr) {
		lr.setStatus(Status.CANCELLED);
		leaverepo.save(lr);
	}
	
	@Override
	public ArrayList<LeaveRecord> findAllPendingLeave() {
		ArrayList<LeaveRecord> list = (ArrayList<LeaveRecord>) leaverepo.findAllPendingLeave();
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
	public ArrayList<LeaveRecord> findLeaveByEmployeeName(String keyword) {
		leaverepo.findLeaveByEmployeeName(keyword);
		return null;
	}
	
	
}
