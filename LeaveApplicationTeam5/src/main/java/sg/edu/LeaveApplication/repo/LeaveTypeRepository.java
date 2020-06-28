package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeRepository extends JpaRepository<LeaveTypes, Integer> {

	ArrayList<LeaveTypes> findAll();
	
	@Query("Select l.leaveName from LeaveTypes l")
	ArrayList<String> findAllLeaveNames();

}
