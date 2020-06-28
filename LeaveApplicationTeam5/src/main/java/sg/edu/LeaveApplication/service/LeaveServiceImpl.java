package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.LeaveRecord;
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
	public LeaveRecord findLeaveRecordByID(Integer id) {
		LeaveRecord leaveRecord = leaverepo.findById(id).get();
		return leaveRecord;
	}
	
	@Override
	public void deleteLeave(LeaveRecord lr) {
		leaverepo.delete(lr);
	}
	
	public void cancelLeave(LeaveRecord lr) {
		lr.setStatus(Status.CANCELLED);
		leaverepo.save(lr);
	}
}
