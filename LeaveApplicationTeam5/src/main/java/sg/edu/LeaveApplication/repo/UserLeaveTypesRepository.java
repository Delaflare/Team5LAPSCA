package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.model.UserLeaveTypes;

public interface UserLeaveTypesRepository extends JpaRepository<UserLeaveTypes, Integer> {

<<<<<<< HEAD
	@Query("Select l from UserLeaveTypes l JOIN l.user u WHERE u.id=(:id)")
	ArrayList<UserLeaveTypes> findByUserId(Integer id);
=======
	ArrayList<UserLeaveTypes> findAllByUser(User user);
>>>>>>> branch 'master' of https://github.com/Delaflare/Team5LAPSCA.git
}
