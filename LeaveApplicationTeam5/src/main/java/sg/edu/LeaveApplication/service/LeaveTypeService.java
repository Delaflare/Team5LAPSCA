package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeService {
               
               public ArrayList<LeaveTypes> findAll();    
               public boolean saveLeaveType(LeaveTypes leavetypes);
               public void deleteLeaveType(LeaveTypes leavetypes);
               public ArrayList<String> findAllLeaveTypeNames();
               public LeaveTypes findLeaveTypesByName(String name);
               public LeaveTypes findLeaveTypesById(Integer id);
}
