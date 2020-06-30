package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.UserLeaveTypes;

public interface UserLeaveTypesService {
	public boolean saveUserLeaveType(UserLeaveTypes uleavetype);
	public ArrayList<UserLeaveTypes> findByUserId(Integer id);

}
