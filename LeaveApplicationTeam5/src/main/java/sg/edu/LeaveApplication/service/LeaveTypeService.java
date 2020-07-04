package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.model.User;

public interface LeaveTypeService {
               
	   public ArrayList<LeaveTypes> findAll();    
	   public ArrayList<String> findAllLeaveTypeNames();
	   public LeaveTypes findLeaveTypesByName(String name);
	   public LeaveTypes findLeaveTypesById(Integer id);
	   public boolean saveLeaveType(LeaveTypes leavetypes);
	   public void deleteLeaveType(LeaveTypes leavetypes);
	   public ArrayList<LeaveTypes> findAllLeaveTypeByUser(User user);  
}
