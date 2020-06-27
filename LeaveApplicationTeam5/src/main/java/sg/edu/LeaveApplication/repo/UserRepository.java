package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByName(String name);
	
	@Query("Select u.name from User u")
	ArrayList<String> findAllUserNames();

}
