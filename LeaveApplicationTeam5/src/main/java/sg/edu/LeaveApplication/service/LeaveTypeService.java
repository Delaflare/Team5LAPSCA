package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeService {
	
	public ArrayList<LeaveTypes> findAll();
	public ArrayList<String> findAllLeaveNames();
}
