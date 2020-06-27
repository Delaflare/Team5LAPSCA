package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.User;
@Service
public class UserServiceImpl implements UserService {

	@Override
	public ArrayList<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> findAllUserNames(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
