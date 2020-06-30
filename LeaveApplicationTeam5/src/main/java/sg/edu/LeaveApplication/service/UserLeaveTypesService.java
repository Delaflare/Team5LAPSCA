package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.User;

import sg.edu.LeaveApplication.model.UserLeaveTypes;

public interface UserLeaveTypesService {
	public boolean saveUserLeaveType(UserLeaveTypes uleavetype);
	public ArrayList<UserLeaveTypes> findByUserId(Integer id);

	public ArrayList<UserLeaveTypes> findAll();

	public ArrayList<UserLeaveTypes> findAllByUser(User user);

	public void update(User user, String leaveName, Integer leaveCost);
	
}
