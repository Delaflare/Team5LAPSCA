package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.model.UserLeaveTypes;

public interface UserLeaveTypesRepository extends JpaRepository<UserLeaveTypes, Integer> {

	@Query("Select l from UserLeaveTypes l JOIN l.user u WHERE u.id=(:id)")
	ArrayList<UserLeaveTypes> findByUserId(Integer id);
	
	ArrayList<UserLeaveTypes> findAllByUser(User user);
	
	
	//@Modifying(clearAutomatically = true)
	//@Query("Update UserLeaveTypes l set l.leaveAllowance = :allowance where l.leaveName = :leaveName "
			//+ "And l.id=(:id)")
	//void updateUserLeaveAllowance(@Param("allowance") Integer allowance, 
			 // @Param("leaveName") String leaveName, @Param("id") Integer id);

}
