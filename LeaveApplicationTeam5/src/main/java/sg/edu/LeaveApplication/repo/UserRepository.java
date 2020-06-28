package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.*;

public interface UserRepository extends JpaRepository<User, Integer> {
	//List<User> findByName(String userName);
	@Query("Select u from User u where u.userName=(:username)")
	ArrayList<User>findByName(String username);
	
	@Query("Select u.userName from User u")
	ArrayList<String> findAllUserNames();

}
