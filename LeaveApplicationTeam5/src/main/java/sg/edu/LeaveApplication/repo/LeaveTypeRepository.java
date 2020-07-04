package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.model.User;

public interface LeaveTypeRepository extends JpaRepository<LeaveTypes, Integer> {
               
               List<LeaveTypes> findByleaveName(String name);
               
               @Query("Select l.leaveName from LeaveTypes l")
               ArrayList<String> findAllLeaveTypeNames();

               @Query("SELECT l FROM LeaveTypes l where l.leaveName In (SELECT DISTINCT leaveName FROM UserLeaveTypes ult WHERE ult.user=:user)")
			ArrayList<LeaveTypes> findAllLeaveTypeByUser(User user);
}