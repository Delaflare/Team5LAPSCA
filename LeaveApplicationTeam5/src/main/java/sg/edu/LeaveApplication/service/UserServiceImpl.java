package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository urepo;
	
	@Override
	public ArrayList<User> findAll() {
		ArrayList<User> list = (ArrayList<User>) urepo.findAll();
		return list;
	}

	@Override
	public boolean saveUser(User user) {
		if(urepo.save(user)!=null) return true; else return false;
	}

	@Override
	public void deleteUser(User user) {
		urepo.delete(user);
	}

	@Override
	public ArrayList<String> findAllUserNames() {
		return urepo.findAllUserNames();
	}

	 @Override public User findUserById(Integer id) { 
		 return urepo.findById(id).get(); 
	 }

	
	@Override
	public User findUserByName(String username) {
		ArrayList<User> list = (ArrayList<User>) urepo.findByName(username);
		System.out.print(list);
		return list.get(0); 
	}

}
