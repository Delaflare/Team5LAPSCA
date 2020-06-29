package sg.edu.LeaveApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.UserLeaveTypes;
import sg.edu.LeaveApplication.repo.UserLeaveTypesRepository;

@Service
public class UserLeaveTypesServiceImpl implements UserLeaveTypesService {
	
	@Autowired
	UserLeaveTypesRepository ulRepo;

	@Override
	public boolean saveUserLeaveType(UserLeaveTypes leavetype) {
		if(ulRepo.save(leavetype) != null) return true; 
		else return false;
	}

}
