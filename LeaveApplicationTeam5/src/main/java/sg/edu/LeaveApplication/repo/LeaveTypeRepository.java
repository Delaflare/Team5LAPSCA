package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeRepository extends JpaRepository<LeaveTypes, Integer> {
               
               List<LeaveTypes> findByleaveName(String name);
               
               @Query("Select l.leaveName from LeaveTypes l")
               ArrayList<String> findAllLeaveTypeNames();
}