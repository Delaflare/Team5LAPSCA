package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveRecord;

public interface LeaveService {
	public boolean saveLeave(LeaveRecord leaverecord);
	public ArrayList<LeaveRecord> findAll();
	public LeaveRecord findLeaveRecordByID(Integer id);
	public void deleteLeave(LeaveRecord lr);
	public void cancelLeave(LeaveRecord lr);
}
