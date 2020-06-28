package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.LeaveRecord;

public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer> {
	
	@Query("Select l from LeaveRecord l where l.status=0")
	ArrayList<LeaveRecord> findAllPendingLeave();
}
