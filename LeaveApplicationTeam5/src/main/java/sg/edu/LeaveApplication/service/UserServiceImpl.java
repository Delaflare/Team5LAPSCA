package sg.edu.LeaveApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.Department;
import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository urepo;
	
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ArrayList<User> findAll() {
		ArrayList<User> list = (ArrayList<User>) urepo.findAll();
		return list;
	}

	@Override
	public void saveUser(User user) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		urepo.save(user);
		
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

	@Override
	public List<User> findUserBydepartment(Department dept) {
		List<User> list = urepo.findAll().stream().filter(d->d.getDepartment() == dept).collect(Collectors.toList());
		return list;
	}
	
}
