package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
	public ArrayList<UserLeaveTypes> findByUserId(Integer id) {
		ArrayList<UserLeaveTypes> list = new ArrayList<UserLeaveTypes>();
		list = ulRepo.findByUserId(id);
		return  list;
	}

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
		for(int i = 0; i<= list.size()-1; i++) {
			if(list.get(i).getLeaveName().equalsIgnoreCase(leaveName)) {
				System.out.println(list.get(i).getLeaveAllowance());
				list.get(i).setLeaveAllowance(newAllowance);
				ulRepo.save(list.get(i));
				System.out.println(list.get(i).getLeaveAllowance());
			}
		};
	}
	
	@Override
	public Integer findleaveAllowance(Integer userId, String leaveName) {
		ArrayList<UserLeaveTypes> ultList = (ArrayList<UserLeaveTypes>) ulRepo.findAll();
		List<UserLeaveTypes> balanceList = ultList.stream()
							.filter(u->u.getUser().getId() == userId && u.getLeaveName().equalsIgnoreCase(leaveName))
							.collect(Collectors.toList());
		
		Integer balance = balanceList.get(0).getLeaveAllowance();
		return balance;
	}

	@Transactional
	@Override
	public void deleteByUser(ArrayList<UserLeaveTypes> ul) {
		
			ulRepo.deleteInBatch(ul);
		
		// TODO Auto-generated method stub
		
	}

//	@Transactional
//	@Override	
//	public void updateUserLeaveAllowance(ArrayList<UserLeaveTypes> ulist) {
//		for (int i = 0; i < ulist.size(); i++) {
//			ulRepo.save(ulist.get(i));
//			//ulRepo.updateUserLeaveAllowance(ulist.get(i).getLeaveAllowance(), ulist.get(i).getLeaveName(), ulist.get(i).getId());
//		}
//		
//		
//		
//	}

	

	

}
