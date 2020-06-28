package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveRecord;

public interface LeaveService {
	public boolean saveLeave(LeaveRecord leaverecord);
	public ArrayList<LeaveRecord> findAll();
}
