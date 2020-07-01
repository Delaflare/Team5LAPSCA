package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.OTRecord;

public interface OTService {
	public ArrayList<OTRecord> findAll();
	public ArrayList<OTRecord> findAllPendingOT();
	
//	public OTRecord findLeaveRecordById(Integer id);
//	public boolean saveLeave(OTRecord OTrecord);
//	public boolean Approve(Integer id, String comment);
//	public void deleteLeave(OTRecord lr);
//	public void cancelLeave(OTRecord lr);
//	public boolean Reject(Integer id, String comment);
//	public ArrayList<String> findAllLeaveStatusName();
//	public ArrayList<OTRecord> findLeaveByEmployeeAndLeave(String keyword, String ltName);
}
