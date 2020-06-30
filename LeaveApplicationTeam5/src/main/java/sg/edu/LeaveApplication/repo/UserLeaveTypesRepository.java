package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.model.UserLeaveTypes;

public interface UserLeaveTypesRepository extends JpaRepository<UserLeaveTypes, Integer> {

	ArrayList<UserLeaveTypes> findAllByUser(User user);
	
}
