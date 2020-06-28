package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeService {
	
	public LeaveTypes findLeaveTypesById(Integer id);
	public ArrayList<LeaveTypes> findAll();
<<<<<<< HEAD
	
	
=======
	public ArrayList<String> findAllLeaveNames();
>>>>>>> branch 'master' of https://github.com/Delaflare/Team5LAPSCA.git
}
