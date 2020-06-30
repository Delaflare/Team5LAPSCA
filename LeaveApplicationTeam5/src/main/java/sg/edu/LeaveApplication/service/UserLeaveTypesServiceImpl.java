package sg.edu.LeaveApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.User;
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

	@Override
	public ArrayList<UserLeaveTypes> findAll(){
		return (ArrayList<UserLeaveTypes>) ulRepo.findAll();
	}

	@Override
	public ArrayList<UserLeaveTypes> findAllByUser(User user) {
		List<UserLeaveTypes> list = ulRepo.findAll().stream()
				.filter(u->u.getUser() == user).collect(Collectors.toList());
		return (ArrayList<UserLeaveTypes>) list;
	}

	@Override
	public void update(User user, String leaveName, Integer newAllowance) {
		ArrayList<UserLeaveTypes> list = ulRepo.findAllByUser(user);
		UserLeaveTypes ult = (UserLeaveTypes) list.stream().filter(u->u.getleaveName()==leaveName);
		ult.setLeaveAllowance(newAllowance);
		ulRepo.save(ult);
	}
	
}
