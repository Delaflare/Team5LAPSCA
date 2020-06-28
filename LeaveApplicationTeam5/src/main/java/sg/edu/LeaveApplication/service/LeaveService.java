package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveRecord;

public interface LeaveService {
	public LeaveRecord findLeaveRecordById(Integer id);
	public ArrayList<LeaveRecord> findAll();
	public ArrayList<LeaveRecord> findAllPendingLeave();
	public boolean saveLeave(LeaveRecord leaverecord);
	public boolean Approve(Integer id);
	public boolean Reject(Integer id, String coment);
	public void deleteLeave(LeaveRecord lr);
	public void cancelLeave(LeaveRecord lr);
}
